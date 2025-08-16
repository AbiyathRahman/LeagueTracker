package com.riot.leaguetracker.service;

import com.riot.leaguetracker.model.Match;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {
    void fetchAndSaveMatchData(String gameId);
    List<Match> getMatchesForSummoner(String puuid);

}
