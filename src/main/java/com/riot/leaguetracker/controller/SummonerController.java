package com.riot.leaguetracker.controller;

import com.riot.leaguetracker.model.Summoner;
import com.riot.leaguetracker.service.impl.RiotApiService;
import com.riot.leaguetracker.service.impl.SummonerServiceImp;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/summoners")
public class SummonerController {


    private final SummonerServiceImp summonerServiceImp;
    private final RiotApiService riotApiService;

    public SummonerController(SummonerServiceImp summonerServiceImp, RiotApiService riotApiService) {
        this.summonerServiceImp = summonerServiceImp;
        this.riotApiService = riotApiService;
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
    @GetMapping("/{puuid}/matches")
    public List<String> getMatchIdUsingPuuid(@PathVariable String puuid){
        if(puuid == null) {
            return Collections.emptyList();
        }
        return riotApiService.getMatchIdUsingPuuid(puuid);
    }
//    @GetMapping("/{puuid}/rank")
//    public ResponseEntity<Summoner> getSummonerRankByPuuid(@PathVariable String puuid){
//        if(puuid == null) {
//            return ResponseEntity.badRequest().build();
//        }
//        Summoner summoner = summonerServiceImp.getSummonerRankByPuuid(puuid);
//        if(summoner != null){
//            return ResponseEntity.ok(summoner);
//        }else{
//            return ResponseEntity.notFound().build();
//        }
//    }
//    @GetMapping("/{puuid}/wins")
//    public ResponseEntity<Summoner> getSummonerWinsByPuuid(@PathVariable String puuid){
//        if(puuid == null) {
//            return ResponseEntity.badRequest().build();
//        }
//        Summoner summoner = summonerServiceImp.getSummonerWinsByPuuid(puuid);
//        if(summoner != null){
//            return ResponseEntity.ok(summoner);
//        }else{
//            return ResponseEntity.notFound().build();
//        }
//    }
//    @GetMapping("/{puuid}/losses")
//    public ResponseEntity<Summoner> getSummonerLossesByPuuid(@PathVariable String puuid){
//        if(puuid == null) {
//            return ResponseEntity.badRequest().build();
//        }
//        Summoner summoner = summonerServiceImp.getSummonerLossesByPuuid(puuid);
//        if(summoner != null){
//            return ResponseEntity.ok(summoner);
//        }else{
//            return ResponseEntity.notFound().build();
//        }
//    }
//    @GetMapping("/{puuid}/level")
//    public ResponseEntity<Summoner> getSummonerLevelByPuuid(@PathVariable String puuid){
//        if(puuid == null) {
//            return ResponseEntity.badRequest().build();
//        }
//        Summoner summoner = summonerServiceImp.getSummonerLevelByPuuid(puuid);
//        if(summoner != null){
//            return ResponseEntity.ok(summoner);
//        }else {
//            return ResponseEntity.notFound().build();
//        }
//    }






}
