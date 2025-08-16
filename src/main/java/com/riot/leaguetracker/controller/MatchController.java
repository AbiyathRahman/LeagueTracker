package com.riot.leaguetracker.controller;

import com.riot.leaguetracker.model.Match;
import com.riot.leaguetracker.service.MatchService;
import com.riot.leaguetracker.service.impl.RiotApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SuppressWarnings("unused")
@RequestMapping("/api/match")
public class MatchController {
    private final RiotApiService riotApiService;
    private final MatchService matchService;
    public MatchController(RiotApiService riotApiService, MatchService matchService) {
        this.riotApiService = riotApiService;
        this.matchService = matchService;
    }
    @GetMapping("/{matchId}")
    public Match getMatchDetails(@PathVariable String matchId){
        if(matchId == null) {
            return null;
        }
        return riotApiService.getMatchDetails(matchId);
    }

    @PostMapping("/fetch/{puuid}")
    public ResponseEntity<String> fetchAndSaveMatchesData(@PathVariable String puuid, @RequestParam(defaultValue = "2") Integer count){
        try{
            matchService.fetchAndSaveMatchesData(puuid, count);
            return ResponseEntity.ok("Matches fetched successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
