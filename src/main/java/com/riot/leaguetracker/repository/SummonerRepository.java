package com.riot.leaguetracker.repository;

import com.riot.leaguetracker.model.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SummonerRepository extends JpaRepository<Summoner, Long> {
    Summoner findByPuuid(String puuid);

    Summoner findByGameNameAndTagLine(String gameName, String tagLine);


}
