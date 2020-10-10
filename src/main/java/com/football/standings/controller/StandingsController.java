package com.football.standings.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.football.standings.model.StandingRequest;
import com.football.standings.model.TeamStandingDTO;
import com.football.standings.service.StandingsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/football/teams/")
public class StandingsController {
	
	private StandingsService standingsService;

	@GetMapping(path = "/check")
	public String healthcheck() {
		return "OK";
	}
	
	@GetMapping(path = "/standing")
	public ResponseEntity<TeamStandingDTO> getTeamStanding(@RequestParam("countryName")String countryName,
			@RequestParam("leagueName")String leagueName, @RequestParam("teamName")String teamName) {
		StandingRequest request = new StandingRequest();
		request.setCountryName(countryName);
		request.setLeagueName(leagueName);
		request.setTeamName(teamName);
		return ResponseEntity.ok(standingsService.getStanding(request));
	}
}
