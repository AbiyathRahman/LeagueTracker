package com.riot.leaguetracker.service;

import com.riot.leaguetracker.model.Match;
import org.springframework.stereotype.Service;

@Service
public interface MatchService {
    Match getMatchByMatchId(String gameId);

}
