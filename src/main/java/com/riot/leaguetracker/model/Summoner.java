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
    @Column(nullable = false, name = "gameName")
    private String gameName;
    @Column(nullable = false, name = "tagLine")
    private String tagLine;
    @Column(nullable = false, name = "region")
    private String region;
    @Column(nullable = false, name = "puuid")
    private String puuid;
    @Column(nullable = true, name = "summonerLevel")
    private Long summonerLevel;
    @Column(nullable = true, name = "wins")
    private Integer wins;
    @Column(nullable = true, name = "losses")
    private Integer losses;
    @Column(nullable = true, name = "rank")
    private String rank;
    @Column(nullable = true, name = "tier")
    private String tier;
}
