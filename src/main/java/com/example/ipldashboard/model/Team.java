package com.example.ipldashboard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String teamName;
    private long totalMatches;
    private long totalWin;

    @Transient
    List<Match> matches;
    public Team(String teamName, long totalMatches) {
        this.teamName =teamName;
        this.totalMatches= totalMatches;

    }
}
