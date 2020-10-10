package com.football.standings.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.football.standings.model.Country;
import com.football.standings.model.League;
import com.football.standings.model.TeamStanding;

@Service
public class ExtFootballApiClient {

	private static final String QUESTION_MARK = "?";
	private static final String EQUALS = "=";
	private static final String APK_KEY_VALUE = "APIKey";

	private final RestTemplate restTemplate;

	@Value("${ext.apifootball.baseurl}")
	private String baseUrl;
	@Value("${ext.apifootball.api.countries}")
	private String countriesAction;
	@Value("${ext.apifootball.api.leagues}")
	private String leaguesAction;
	@Value("${ext.apifootball.api.standings}")
	private String standingsAction;
	@Value("${ext.apifootball.apikey}")
	private String apiKey;

	@Autowired
	public ExtFootballApiClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Country[] getCountries() {
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("acion", this.countriesAction);
		String url = getUrl(this.baseUrl, queryParams);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		Country[] countries = this.restTemplate
				.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Country[].class).getBody();
		return countries;
	}

	public League[] getLeagues(int countryId) {
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("acion", this.leaguesAction);
		queryParams.put("country_id", String.valueOf(countryId));
		String url = getUrl(this.baseUrl, queryParams);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		League[] leagues = this.restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), League[].class)
				.getBody();
		return leagues;
	}

	public TeamStanding[] getTeamStanding(int leagueId) {
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("acion", this.standingsAction);
		queryParams.put("league_id", String.valueOf(leagueId));
		String url = getUrl(this.baseUrl, queryParams);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		TeamStanding[] standings = this.restTemplate
				.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), TeamStanding[].class).getBody();
		return standings;
	}

	private String getUrl(String baseUrl, Map<String, String> queryParams) {
		String url = baseUrl + QUESTION_MARK;
		queryParams.put(APK_KEY_VALUE, this.apiKey);
		for (Entry<String, String> q : queryParams.entrySet()) {
			url += q.getKey() + EQUALS + q.getValue();
		}
		return url;
	}
}
