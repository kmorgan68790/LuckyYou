import React, { useEffect, useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import NumerologyList from '../components/NumerologyList';

const Numerology = ({user}) => {
    const [numerologies, setNumerologies] = useState([])

	useEffect(() => {
		fetch("http://localhost:8080/api/numerology")
		.then(response => response.json())
		.then(json => {
			setNumerologies(json)
		})
	}, [])

    return (
        <NumerologyList numerologies={numerologies} user={user}/>
    )
};

export default Numerology;