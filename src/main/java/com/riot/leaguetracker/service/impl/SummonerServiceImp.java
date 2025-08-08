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

}
