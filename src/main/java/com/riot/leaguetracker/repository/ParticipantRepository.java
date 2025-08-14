package com.riot.leaguetracker.repository;

import com.riot.leaguetracker.model.Participants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participants, Long> {
    List<Participants> findByMatchId(String matchId);
    List<Participants> findBySummonerIdAndWin(String puuid, Boolean win);
    List<Participants> findBySummonerId(String puuid);

}
