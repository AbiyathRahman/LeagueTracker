package com.riot.leaguetracker.repository;

import com.riot.leaguetracker.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, String> {
    Optional<Match> getMatchByGameId(String gameId);
    List<Match> getRangeOfMatches(String matchId, Integer range);
}
