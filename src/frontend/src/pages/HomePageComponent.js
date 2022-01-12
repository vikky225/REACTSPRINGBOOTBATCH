import {React,useEffect,useState} from 'react'
import { TeamTileComponent } from '../components/TeamTileComponent';


import './HomePageComponent.scss';



export const  HomePageComponent= () =>{


 
   const [teams, setTeams] = useState([]);
   
   
    useEffect(() => {

        const fetchAllTeams = async () =>{
            const response = fetch(`http://localhost:8088/teams`);
            const data = await (await response).json();
            console.log(data);
            setTeams(data);
        };
        fetchAllTeams();
    
        }, []
    );

  


    return (
       
        <div className="HomePageComponent">
            <div className="header-section"> 
            <h1 className="app-name">Vikas IPL Dashboard</h1>  
            </div>
            <div className="team-grid">{
                teams.map(team=> <TeamTileComponent teamName={team.teamName} />) 
            }</div>
            
         

        </div>
    )
}