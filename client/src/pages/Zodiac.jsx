import React, { useEffect, useState } from 'react';
import ZodiacList from '../components/ZodiacList';

const Zodiac = ({user}) => {
    const [zodiacs, setZodiacs] = useState([])

	useEffect(() => {
		fetch("http://localhost:8080/api/zodiac")
		.then(response => response.json())
		.then(json => {
			setZodiacs(json)
		})
	}, [])

    return (
        <ZodiacList zodiacs={zodiacs} user={user}/>
    )
};

export default Zodiac;

