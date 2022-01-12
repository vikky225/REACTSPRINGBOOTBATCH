import {React,useEffect,useState} from 'react'
import { useParams } from 'react-router';
import { MatchDetailCardComponent } from '../components/MatchDetailCardComponent'
import {MatchSmallCardComponent } from '../components/MatchSmallCardComponent'
import './TeamPageComponent.scss';
import {Link} from 'react-router-dom';


export const  TeamPageComponent= () =>{


 
   const [team, setTeam] = useState({matches: []});
   const {teamName} = useParams();
   
    useEffect(() => {

        const fetchTeams = async () =>{
            const response = fetch(`http://localhost:8088/team/${teamName}`);
            const data = await (await response).json();
            console.log(data);
            setTeam(data);
        };
        fetchTeams();
    
        }, [teamName]
    );

    if(!team || !team.teamName){return <h1>Team not found</h1>}


    return (
       
        <div className="TeamPageComponent">
            <div className="team-name-section"> <h1 className="team-name">{team.teamName}</h1>  
            </div>
            
            <div className="win-loss-section">win/Loss
    
            
            </div>

            <div className="match-detail-section">  
            <h3>Latest Matches</h3>
            <MatchDetailCardComponent  teamName={team.teamName} match={team.matches[0]} />
            </div>
            
            {team.matches.slice(1).map (match=><MatchSmallCardComponent key={match.id} teamName={team.teamName} match={match}/>)}
          <div className="more-link">
              <Link to={`/teams/${teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}>More</Link>
           
          </div>

        </div>
    )
}

