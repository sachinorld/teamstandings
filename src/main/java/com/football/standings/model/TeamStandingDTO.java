package com.football.standings.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TeamStandingDTO {
	@JsonProperty("team_id")
	private int teamId;
	@JsonProperty("team_name")
	private String teamName;
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
	
	
	public static TeamStandingDTO from(TeamStanding teamStanding) {
		TeamStandingDTO dto = new TeamStandingDTO();
		if (Objects.nonNull(teamStanding)) {
			dto.setCountryId(teamStanding.getCountryId());
			dto.setCountryName(teamStanding.getCountryName());
			dto.setLeagueId(teamStanding.getLeagueId());
			dto.setLeagueName(teamStanding.getLeagueName());
			dto.setTeamId(teamStanding.getTeamId());
			dto.setTeamName(teamStanding.getTeamName());
			dto.setOverallLeaguePosition(teamStanding.getOverallLeaguePosition());
		}
		return dto;
	}
}
