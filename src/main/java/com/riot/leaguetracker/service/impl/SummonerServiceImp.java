package com.riot.leaguetracker.service.impl;

import com.riot.leaguetracker.model.Summoner;
import com.riot.leaguetracker.repository.SummonerRepository;
import com.riot.leaguetracker.service.SummonerService;
import org.springframework.beans.factory.annotation.Autowired;

public class SummonerServiceImp implements SummonerService {

    @Autowired
    private SummonerRepository summonerRepository;

    @Override
    public Summoner createSummonerByGameName(String gameName, String tagLine) {
        return null;
    }

}
