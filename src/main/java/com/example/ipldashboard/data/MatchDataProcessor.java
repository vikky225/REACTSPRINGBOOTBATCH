package com.example.ipldashboard.data;

import com.example.ipldashboard.model.Match;
import org.springframework.batch.item.ItemProcessor;
import java.time.LocalDate;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {


    @Override
    public Match process(final MatchInput matchInput) throws Exception {
        Match match = new Match();
        match.setId(Long.parseLong(matchInput.getId()));
        match.setCity(matchInput.getCity());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setMatchwinner(matchInput.getWinner());
        match.setPlayerofmatch(matchInput.getPlayer_of_match());
        match.setVenue(matchInput.getVenue());

        // set team 1 and team 2 depending on the inning order
        String firstInningTeam, secondInningTeam;
        if ("bat".equals(matchInput.getToss_decision())) {
            firstInningTeam = matchInput.getToss_winner();
            secondInningTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2() : matchInput.getTeam1();
        } else {
            secondInningTeam = matchInput.getToss_winner();
            firstInningTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2() : matchInput.getTeam1();
        }
        match.setTeam1(firstInningTeam);
        match.setTeam2(secondInningTeam);

        match.setTossdecision(matchInput.getToss_decision());
        match.setTosswinner(matchInput.getToss_winner());
        match.setResult(matchInput.getResult());
        match.setResultmargin(matchInput.getResult_margin());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());
        return match;
    }
}
