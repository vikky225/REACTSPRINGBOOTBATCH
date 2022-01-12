import {React,useEffect,useState} from 'react'
import { useParams } from 'react-router';
import { MatchDetailCardComponent } from '../components/MatchDetailCardComponent';
import { YearSelectorComponent } from '../components/YearSelectorComponent';
import './MatchPageComponent.scss';

export const MatchPageComponent=()=> {

    const [matches, setMatches] = useState([]);
     const {teamName,year}= useParams();
    useEffect(() => {

        const fetchMatches = async () =>{
            const response = fetch(`http://localhost:8088/team/${teamName}/matches?year=${year}`);
            const data = await (await response).json();
            console.log(data);
            setMatches(data);
        };
        fetchMatches();
    
        }, [teamName,year]
    );

    return (
        <div className="MatchPageComponent">
            <div className="year-selector"> 
            <h3>Select Year</h3>
            <YearSelectorComponent teamName={teamName}/>
           
            </div>
           
            <div>
           <h1 className="page-heading">{teamName} matches in {year}</h1>
            {
               matches.map(match => <MatchDetailCardComponent key={match.id} teamName={teamName} match={match}/>)
            }
            </div>
           
 
        </div>
    )
}


