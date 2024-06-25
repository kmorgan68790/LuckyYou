import React, { useEffect, useState } from 'react';
import { Routes, Route, json } from 'react-router-dom';
import ConcordList from '../components/ConcordList'

const Concord = ({user}) => {
    const [concords, setConcords] = useState([])

    const [concordDays, setConcordDays] = useState({})


	useEffect(() => {
		fetch("http://localhost:8080/api/concord-group")
		.then(response => response.json())
		.then(json => {
			setConcords(json)
		})
	}, [])

    useEffect(() => { 
        
            // concords.map(concord => {fetch(`http://localhost:8080/api/concord-days/group/${concords.concordGroupId}`)
            // .then(response => response.json())
            // .then(json => {
            //     setConcordDays(json)
            // })}
            const days = {}
            Promise.all (
            concords.map(concord => {
                fetch(`http://localhost:8080/api/concord-days/group/${concord.concordGroupId}`)
                .then(response => response.json())
                
                .then(json => { 
                    // setConcordDays({...concordDays, [concord.concordGroupId]: json})
                    days [concord.concordGroupId] = json
                }
            )})
            )
            .then(() => setConcordDays(days))
    }, [concords])
    
    return (
        <>
        {/* <ConcordList concords={concords} user={user}/> */}
        <ConcordList concords={concords} concordDays={concordDays} user={user}/>
        </>
    )
};

export default Concord;