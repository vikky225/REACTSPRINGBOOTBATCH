package com.example.ipldashboard.controller;

import com.example.ipldashboard.model.Match;
import com.example.ipldashboard.model.Team;
import com.example.ipldashboard.repository.MatchRepository;
import com.example.ipldashboard.repository.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@CrossOrigin
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

     @Autowired
    private MatchRepository matchRepository;


    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable  String teamName){

        Team team = this.teamRepository.findByTeamName(teamName);

        team.setMatches(this.matchRepository.findLatestMatchesbyTeam(teamName,4));
        return team;
    }


    @GetMapping("/teams")
    public Iterable<Team> getAllTeam(){

        Iterable<Team> all = this.teamRepository.findAll();


        return all;
    }


    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesforTeam(@PathVariable  String teamName, @RequestParam int year){
         LocalDate startDate = LocalDate.of(year,1,1);
         LocalDate endDate = LocalDate.of(year+1,1,1);

      //  List<Match> byTeam1OrTeam2AndDateBetweenOrderByDateDesc = this.matchRepository.getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(teamName,startDate, endDate,teamName,startDate, endDate);

        List<Match> byTeam1OrTeam2AndDateBetweenOrderByDateDesc =      this.matchRepository.getMatchesByTeamBetweenDates(teamName,startDate,endDate);
        return byTeam1OrTeam2AndDateBetweenOrderByDateDesc;
    }

}
