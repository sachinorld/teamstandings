package com.football.standings.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class League {
	@JsonProperty("league_id")
	private int leagueId;
	@JsonProperty("league_name")
	private String leagueName;
	@JsonProperty("country_id")
	private int countryId;
	public int getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}
	public String getLeagueName() {
		return leagueName;
	}
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
}
