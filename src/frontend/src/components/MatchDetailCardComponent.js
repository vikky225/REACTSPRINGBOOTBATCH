import React from 'react';
import { Link } from 'react-router-dom';
import './MatchDetailCardComponent.scss';

 export const MatchDetailCardComponent=({teamName,match})=>{
    if(!match)  return null;
    const otherTeam= match.team1 ===teamName ? match.team2 :match.team1
    const otherTeamRoute= `/teams/${otherTeam}`;
    const isMatchWon =teamName === match.matchwinner;

    return (
        <div className={isMatchWon ?'MatchDetailCardComponent won-card':'MatchDetailCardComponent lost-card'}>
            <div>
            <span className="vs">vs</span>
             <h1><Link to={otherTeamRoute}>{otherTeam}</Link></h1>

            <h2 className="match-date">{match.date}</h2>
            <h3 className="match-venue">{match.venue}</h3>
            <h3 className="match-winner">{match.matchwinner} won by {match.resultmargin} {match.result}</h3>
            


            </div>

            <div className="additional-details">
            <h3>First Inning</h3>
            <p>{match.team1}</p>
            <h3>Second Inning</h3>
             <p>{match.team2}</p>
             <h3>Man of the Match </h3>
            <p>{match.playerofmatch}</p>
            <h3>Umpires</h3>
             <p>{match.umpire1} , {match.umpire2}</p>
             </div>

            </div>
            
       
    )
}


