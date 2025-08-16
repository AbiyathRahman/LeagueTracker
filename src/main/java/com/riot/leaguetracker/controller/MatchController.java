package com.riot.leaguetracker.controller;

import com.riot.leaguetracker.model.Match;
import com.riot.leaguetracker.service.impl.RiotApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("unused")
@RequestMapping("/api/match")
public class MatchController {
    private final RiotApiService riotApiService;
    public MatchController(RiotApiService riotApiService) {
        this.riotApiService = riotApiService;
    }
    @GetMapping("/{matchId}")
    public Match getMatchDetails(@PathVariable String matchId){
        if(matchId == null) {
            return null;
        }
        return riotApiService.getMatchDetails(matchId);
    }
}
