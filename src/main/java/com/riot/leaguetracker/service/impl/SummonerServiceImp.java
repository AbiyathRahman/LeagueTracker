package com.riot.leaguetracker.service.impl;

import com.riot.leaguetracker.model.Summoner;
import com.riot.leaguetracker.repository.SummonerRepository;
import com.riot.leaguetracker.service.SummonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SummonerServiceImp implements SummonerService {

    @Autowired
    private SummonerRepository summonerRepository;


    private final RiotApiService riotApiService;
    public SummonerServiceImp(RiotApiService riotApiService) {
        this.riotApiService = riotApiService;
    }


    @Override
    public Summoner createSummonerByGameName(String gameName, String tagLine) {

        String puuid = riotApiService.getPuuidByRIotId(gameName, tagLine);
        Summoner summoner = summonerRepository.findByPuuid(puuid);
        if(summoner == null){
            summoner = new Summoner();
            summoner.setGameName(gameName);
            summoner.setTagLine(tagLine);
            summoner.setPuuid(puuid);
            summoner.setRegion("NA");
            summoner.setTier(riotApiService.getSummonerTierByPuuid(puuid));
            summoner.setRank(riotApiService.getSummonerRankByPuuid(puuid));
            summoner.setWins(riotApiService.getSummonerWinsByPuuid(puuid));
            summoner.setLosses(riotApiService.getSummonerLossesByPuuid(puuid));
            summoner.setSummonerLevel(riotApiService.getSummonerLeveledUpByPuuid(puuid));
            summonerRepository.save(summoner);
        }
        return summoner;
    }
    @Override
    public Summoner getSummonerTierByPuuid(String puuid) {
        String tier = riotApiService.getSummonerTierByPuuid(puuid);
        Summoner summoner = summonerRepository.findByPuuid(puuid);
        if(summoner != null){
            summoner.setTier(tier);
            summonerRepository.save(summoner);
        }
        return summoner;
    }
    @Override
    public Summoner getSummonerRankByPuuid(String puuid) {
        String rank = riotApiService.getSummonerRankByPuuid(puuid);
        Summoner summoner = summonerRepository.findByPuuid(puuid);
        if(summoner != null){
            summoner.setRank(rank);
            summonerRepository.save(summoner);
        }
        return summoner;
    }
    @Override
    public Summoner getSummonerWinsByPuuid(String puuid) {
        Integer wins = riotApiService.getSummonerWinsByPuuid(puuid);
        Summoner summoner = summonerRepository.findByPuuid(puuid);
        if(summoner != null){
            summoner.setWins(wins);
            summonerRepository.save(summoner);
        }
        return summoner;
    }
    @Override
    public Summoner getSummonerLossesByPuuid(String puuid) {
        Integer losses = riotApiService.getSummonerLossesByPuuid(puuid);
        Summoner summoner = summonerRepository.findByPuuid(puuid);
        if(summoner != null){
            summoner.setLosses(losses);
            summonerRepository.save(summoner);
        }
        return summoner;
    }
    @Override
    public Summoner getSummonerLevelByPuuid(String puuid){
        Long level = riotApiService.getSummonerLeveledUpByPuuid(puuid);
        Summoner summoner = summonerRepository.findByPuuid(puuid);
        if(summoner != null){
            summoner.setSummonerLevel(level);
            summonerRepository.save(summoner);
        }
        return summoner;
    }



}
