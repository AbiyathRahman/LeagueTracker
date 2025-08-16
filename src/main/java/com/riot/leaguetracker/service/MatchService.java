package com.riot.leaguetracker.service;

import com.riot.leaguetracker.model.Match;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {
    void fetchAndSaveMatchesData(String puuid, Integer count);
    //List<Match> getMatchesForSummoner(String puuid);

}
