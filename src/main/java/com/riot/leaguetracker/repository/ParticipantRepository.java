package com.riot.leaguetracker.repository;

import com.riot.leaguetracker.model.Match;
import com.riot.leaguetracker.model.Participants;
import com.riot.leaguetracker.model.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participants, Long> {
    List<Participants> findByMatch(Match match);
    List<Participants> findBySummonerAndWin(Summoner summoner, Boolean win);
    List<Participants> findBySummoner(Summoner summoner);

}
