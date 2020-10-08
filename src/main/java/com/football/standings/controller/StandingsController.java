package com.football.standings.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/football/teams/")
public class StandingsController {

	@GetMapping(path = "/check")
	public String healthcheck() {
		return "OK";
	}
	
	@GetMapping(path = "/standing")
	public ResponseEntity<String> getTeamStanding() {
		
		return ResponseEntity.ok("Standing: 1");
	}
}
