package com.riot.leaguetracker.service;

import com.riot.leaguetracker.model.Summoner;
import org.springframework.stereotype.Service;

@Service
public interface RiotApiService {
    String getPuuidByRIotId(String gameName, String tagLine);
    //String getSummonerByPuuid(String puuid);
    //String getPlayerRankAndTier(String puuid)

}
