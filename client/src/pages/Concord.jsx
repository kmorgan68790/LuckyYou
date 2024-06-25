import React, { useEffect, useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import ConcordList from '../components/ConcordList'

const Concord = ({user}) => {
    const [concords, setConcords] = useState([])

    const [concordDays, setConcordDays] = useState([])


	useEffect(() => {
		fetch("http://localhost:8080/api/concord-group")
		.then(response => response.json())
		.then(json => {
			setConcords(json)
		})
	}, [])

    useEffect(() => {
    fetch(`http://localhost:8080/api/concord-days/group/${concords.concordGroupId}`)
		.then(response => response.json())
		.then(json => {
			setConcordDays(json)
		})
    }, [])
    
    return (
        <>
        <ConcordList concords={concords} user={user}/>
        <ConcordList concordDays={concordDays} user={user}/>
        </>
    )
};

export default Concord;