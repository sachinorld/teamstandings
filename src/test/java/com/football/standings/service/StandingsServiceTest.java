package com.football.standings.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.football.standings.exception.ApplicationException;
import com.football.standings.model.Country;
import com.football.standings.model.League;
import com.football.standings.model.StandingRequest;
import com.football.standings.model.TeamStanding;
import com.football.standings.model.TeamStandingDTO;

public class StandingsServiceTest {

	@InjectMocks
	private StandingsService standingsService;
	@Mock
	private ExtFootballApiClient extFootballApiClient;
	
	@BeforeAll
	public static void setup() {
		System.out.println("setup all");
	}
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetTeamStanding() {
		StandingRequest request = getStandingsRequestPositive();
		Country[] expectedCountries = getExcpectedCountries();
		Mockito.when(extFootballApiClient.getCountries()).thenReturn(expectedCountries);
		League[] expectedLeagues = getExcpectedLeagues(expectedCountries[0]);
		Mockito.when(extFootballApiClient.getLeagues(expectedCountries[0].getCountryId())).thenReturn(expectedLeagues);
		TeamStanding[] teamStandings = getExpectedTeamStandings(expectedCountries[0], expectedLeagues[0]);
		Mockito.when(extFootballApiClient.getTeamStanding(expectedLeagues[0].getLeagueId())).thenReturn(teamStandings);
		TeamStandingDTO dto = standingsService.getStanding(request);
		
		assertEquals(teamStandings[0].getLeagueName(), dto.getLeagueName());
		assertEquals(teamStandings[0].getTeamId(), dto.getTeamId());
	}
	
	@Test
	public void testGetTeamStandingApplicationException() {
		StandingRequest request = getStandingsRequestPositive();
		Country[] expectedCountries = getExcpectedInvalidCountry();
		Mockito.when(extFootballApiClient.getCountries()).thenReturn(expectedCountries);
		assertThrows(ApplicationException.class, () ->{
			TeamStandingDTO dto = standingsService.getStanding(request);
			assertFalse(dto != null);
		});
	}

	private Country[] getExcpectedInvalidCountry() {
		Country c = new Country();
		c.setCountryId(20);
		c.setCountryName("Brazil");
		return new Country[] {c};
	}

	private Country[] getExcpectedCountries() {
		Country c = new Country();
		c.setCountryId(1);
		c.setCountryName("India");
		return new Country[] {c};
	}
	
	private League[] getExcpectedLeagues(Country c) {
		League l = new League();
		l.setCountryId(c.getCountryId());
		l.setLeagueId(10);
		l.setLeagueName("Ranji");
		return new League[] {l};
	}
	
	private TeamStanding[] getExpectedTeamStandings(Country c, League l) {
		TeamStanding ts = new TeamStanding();
		ts.setCountryId(c.getCountryId());
		ts.setLeagueId(l.getLeagueId());
		ts.setTeamId(11);
		ts.setTeamName("Karnataka");
		ts.setCountryName("India");
		ts.setLeagueName("Ranji");
		ts.setOverallLeaguePosition(1);
		return new TeamStanding[] {ts};
	}

	private StandingRequest getStandingsRequestPositive() {
		StandingRequest request = new StandingRequest();
		request.setCountryName("India");
		request.setLeagueName("Ranji");
		request.setTeamName("Karnataka");
		return request;
	}
}
