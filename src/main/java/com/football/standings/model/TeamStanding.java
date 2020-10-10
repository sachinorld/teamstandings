package com.football.standings.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TeamStanding implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2847580809499253353L;
	@JsonProperty("team_id")
	private int teamId;
	@JsonProperty("team_name")
	private String teamName;
	@JsonProperty("league_id")
	private int leagueId;
	@JsonProperty("league_name")
	private String leagueName;
	@JsonProperty("country_id")
	private int countryId;
	@JsonProperty("country_name")
	private String countryName;
	@JsonProperty("overall_league_position")
	private int overallLeaguePosition;
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
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
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public int getOverallLeaguePosition() {
		return overallLeaguePosition;
	}
	public void setOverallLeaguePosition(int overallLeaguePosition) {
		this.overallLeaguePosition = overallLeaguePosition;
	}
	
}
