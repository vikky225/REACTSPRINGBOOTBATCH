package com.example.ipldashboard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Match {
    @Id
    private long id;
    private String city;
    private String playerofmatch;
    private String venue;
    private LocalDate date;
    private String team1;
    private String team2;
    private String tosswinner;
    private String tossdecision;
    private String matchwinner;
    private String result;
    private String resultmargin;
    private String umpire1;
    private String umpire2;


}
