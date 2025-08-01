package com.riot.leaguetracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="summoner")
@Getter
@Setter
public class Summoner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gameName;
    private String tagLine;
    private String region;
    private String puuid;
    private Long summonerLevel;
    private Integer wins;
    private Integer losses;
    private String rank;
    private String tier;
}
