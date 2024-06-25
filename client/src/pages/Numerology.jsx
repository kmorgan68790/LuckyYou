import React, { useEffect, useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import NumerologyList from '../components/NumerologyList';

const Numerology = ({user}) => {
    const [numerologies, setNumerologies] = useState([])
	const [numerologyNumbers, setnumerologyNumbers] = useState([])

	useEffect(() => {
		fetch("http://localhost:8080/api/numerology")
		.then(response => response.json())
		.then(json => {
			setNumerologies(json)
		})
	}, [])

	useEffect(() => {
		fetch("http://localhost:8080/api/mapping/user/${user.userId}")
		.then(response => response.json())
		.then(json => {
			setnumerologyNumbers(json)
		})
	}, [])

    return (
        <NumerologyList numerologies={numerologies} numerologyNumbers={numerologyNumbers} user={user}/>
    )
};

export default Numerology;