package com.riot.leaguetracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@Table(name="match")
@Entity
@Getter
@Setter
public class Match {
    @Id
    @Column(name="matchId")
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
