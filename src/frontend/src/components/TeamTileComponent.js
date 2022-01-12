import React from 'react'
import { Link } from 'react-router-dom'
import './TeamTileComponent.scss'

export const  TeamTileComponent=({teamName})=> {
    return (
        <div className="TeamTileComponent">
           <Link to={`teams/${teamName}`}>{teamName}</Link>
            
        </div>
    )
}


