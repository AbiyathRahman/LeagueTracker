package com.riot.leaguetracker.controller;

import com.riot.leaguetracker.model.Summoner;
import com.riot.leaguetracker.service.impl.SummonerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/summoners")
public class SummonerController {


    private final SummonerServiceImp summonerServiceImp;
    public SummonerController(SummonerServiceImp summonerServiceImp) {
        this.summonerServiceImp = summonerServiceImp;
    }

    @PostMapping("/{gameName}/{tagLine}")
    public ResponseEntity<Summoner> createSummoner(@PathVariable String gameName, @PathVariable String tagLine){
        if(gameName == null || tagLine == null) {
            return ResponseEntity.badRequest().build();
        }
        Summoner summoner = summonerServiceImp.createSummonerByGameName(gameName, tagLine);
        if(summoner != null){
            return ResponseEntity.ok(summoner);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/{puuid}")
    public ResponseEntity<Summoner> getSummonerTierByPuuid(@PathVariable String puuid){
        if(puuid == null) {
            return ResponseEntity.badRequest().build();
        }
        Summoner summoner = summonerServiceImp.getSummonerTierByPuuid(puuid);
        if(summoner != null){
            return ResponseEntity.ok(summoner);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/{puuid}/rank")
    public ResponseEntity<Summoner> getSummonerRankByPuuid(@PathVariable String puuid){
        if(puuid == null) {
            return ResponseEntity.badRequest().build();
        }
        Summoner summoner = summonerServiceImp.getSummonerRankByPuuid(puuid);
        if(summoner != null){
            return ResponseEntity.ok(summoner);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{puuid}/wins")
    public ResponseEntity<Summoner> getSummonerWinsByPuuid(@PathVariable String puuid){
        if(puuid == null) {
            return ResponseEntity.badRequest().build();
        }
        Summoner summoner = summonerServiceImp.getSummonerWinsByPuuid(puuid);
        if(summoner != null){
            return ResponseEntity.ok(summoner);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{puuid}/losses")
    public ResponseEntity<Summoner> getSummonerLossesByPuuid(@PathVariable String puuid){
        if(puuid == null) {
            return ResponseEntity.badRequest().build();
        }
        Summoner summoner = summonerServiceImp.getSummonerLossesByPuuid(puuid);
        if(summoner != null){
            return ResponseEntity.ok(summoner);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{puuid}/level")
    public ResponseEntity<Summoner> getSummonerLevelByPuuid(@PathVariable String puuid){
        if(puuid == null) {
            return ResponseEntity.badRequest().build();
        }
        Summoner summoner = summonerServiceImp.getSummonerLevelByPuuid(puuid);
        if(summoner != null){
            return ResponseEntity.ok(summoner);
        }else {
            return ResponseEntity.notFound().build();
        }
    }






}
