package com.riot.leaguetracker.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name="match")
@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String matchId;
    private String gameMode;
    private String queueType;
    private Long gameDuration;
    private LocalDateTime gameCreation;
    private String gameVersion;

    // Match outcome
    private Integer winningTeam;

    //Time stamps
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //Relationships
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Participants> participants = new ArrayList<>();

}
