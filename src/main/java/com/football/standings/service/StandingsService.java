package com.football.standings.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.football.standings.exception.ApplicationException;
import com.football.standings.model.Country;
import com.football.standings.model.League;
import com.football.standings.model.StandingRequest;
import com.football.standings.model.TeamStanding;
import com.football.standings.model.TeamStandingDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StandingsService {

	private ExtFootballApiClient extFootballApiClient;

	@Autowired
	public StandingsService(ExtFootballApiClient extFootballApiClient) {
		this.extFootballApiClient = extFootballApiClient;
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public TeamStandingDTO getStanding(StandingRequest request) {
		// country from name
		Optional<Country> countryOpt = getCountry(request);
		TeamStanding standingsDefault = getTeamStandingDefaultResponse(request);
		if (!isValidCountry(countryOpt, standingsDefault, request)) {
			return TeamStandingDTO.from(standingsDefault);
		}
		Country country = countryOpt.get();
		standingsDefault.setCountryId(country.getCountryId());

		// league in country
		Optional<League> leagueOpt = getLeague(country, request);
		if (!isValidLeague(leagueOpt, standingsDefault, request)) {
			return TeamStandingDTO.from(standingsDefault);
		}
		League league = leagueOpt.get();
		standingsDefault.setLeagueId(league.getLeagueId());

		// team from country and league id
		TeamStanding s = getTeamStanding(league, request);
		s.setCountryId(country.getCountryId());
		s.setLeagueId(league.getLeagueId());
		if (s.getTeamId() == 0) {
			return TeamStandingDTO.from(standingsDefault);
		}
		return TeamStandingDTO.from(s);
	}

	public TeamStanding getTeamStanding(League league, StandingRequest request) {
		List<TeamStanding> standings = new ArrayList<TeamStanding>(
				Arrays.asList(extFootballApiClient.getTeamStanding(league.getLeagueId())));
		return standings.stream().filter(s -> s.getTeamName().equals(request.getTeamName())).findFirst().orElse(null);
	}

	public Optional<League> getLeague(Country country, StandingRequest request) {
		List<League> leagues = new ArrayList<League>(
				Arrays.asList(extFootballApiClient.getLeagues(country.getCountryId())));
		return leagues.stream().filter(l -> l.getLeagueName().equals(request.getLeagueName())).findFirst();
	}

	private boolean isValidLeague(Optional<League> leagueOpt, TeamStanding standings, StandingRequest request) {
		if (leagueOpt.isEmpty()) {
			throw new ApplicationException("League not found: " + request.getLeagueName());
		}
		if (leagueOpt.get().getLeagueId() == 0) {
			standings.setCountryId(0);
			return false;
		}
		return true;
	}

	private boolean isValidCountry(Optional<Country> countryOpt, TeamStanding standings, StandingRequest request) {
		if (countryOpt.isEmpty()) {
			throw new ApplicationException("Country not found: " + request.getCountryName());
		}
		if (countryOpt.get().getCountryId() == 0) {
			standings.setCountryId(0);
			return false;
		}
		return true;
	}

	public Optional<Country> getCountry(StandingRequest request) {
		List<Country> countries = new ArrayList<Country>(Arrays.asList(extFootballApiClient.getCountries()));
		return countries.stream().filter(c -> c.getCountryName().equals(request.getCountryName())).findFirst();
	}

	private TeamStanding getTeamStandingDefaultResponse(StandingRequest request) {
		TeamStanding teamStanding = new TeamStanding();
		teamStanding.setCountryName(request.getCountryName());
		teamStanding.setLeagueName(request.getLeagueName());
		teamStanding.setTeamName(request.getTeamName());
		return teamStanding;
	}
}
