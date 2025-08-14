package com.riot.leaguetracker.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
@Table(name="participants")
@Entity
public class Participants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Performance
    private Integer kills;
    private Integer deaths;
    private Integer assists;
    private Integer totalMinionsKilled;
    private Integer totalDamageDealt;
    private Integer totalDamageDealtToChampions;
    private Integer goldEarned;
    //Game-Specific details
    private String gameId;
    private Integer championLevel;
    private String championName;
    private String position;
    private Integer teamId;
    private boolean win;

    //Items
    private String items;

    private LocalDateTime createdAt;

    //Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="matchId")
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="summoner_puuid", referencedColumnName = "puuid")
    private Summoner summoner;


}
