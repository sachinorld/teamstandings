package com.football.standings.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class StandingRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1829854836585495743L;
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getLeagueName() {
		return leagueName;
	}
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	private String countryName;
	private String leagueName;
	private String teamName;
}
