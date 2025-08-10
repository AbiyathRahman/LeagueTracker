package com.riot.leaguetracker.service;

import com.riot.leaguetracker.model.Summoner;
import org.springframework.stereotype.Service;

@Service
public interface SummonerService {
    Summoner createSummonerByGameName(String gameName, String tagLine);
    Summoner getSummonerTierByPuuid(String puuid);
    Summoner getSummonerRankByPuuid(String puuid);
    //Summoner getSummonerByGameNameAndTagLine(String gameName, String tagLine);
    //Summoner saveSummoner(Summoner summoner);
    //Summoner updateSummoner(Summoner summoner);
    //void deleteSummoner(Summoner summoner);
}
