import React from 'react';
import { Link } from 'react-router-dom';
import './MatchSmallCardComponent.scss'; 


export const MatchSmallCardComponent=({teamName,match})=> {
    
    
    if(!match)  return null;

    const otherTeam= match.team1 ===teamName ? match.team2 :match.team1
    const otherTeamRoute= `/teams/${otherTeam}`;
    const isMatchWon =teamName === match.matchwinner;

    return (
<div className={isMatchWon ?'MatchSmallCardComponent won-card':'MatchSmallCardComponent lost-card'}>


<span className="vs">vs</span>
             <h4><Link to={otherTeamRoute}>{otherTeam}</Link></h4>

<p className="matchResult">{match.matchwinner} won by {match.resultmargin} {match.result}</p>
            
</div>            
       
    )
}

