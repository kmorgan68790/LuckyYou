import React, { useEffect, useState } from 'react';
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
		<div className='me-5 mx-5'>
		<h1 className='mb-4'>Numerology: "It's all about the digits" </h1>
		<p>Your Numerology chart is mostly based on your birth date and your full name, so everyone's numbers will be unique to them. 
			Some numerology numbers will indicate your strengths and weaknesses, while others influence your personality traits or the 
			opportunities that come into your life. Here are the 5 core numbers with the addition of the lucky numbers and lucky years:</p>
		<ul>
			<p>Life Path number: Your Life Path is the most important Numerology number in your chart. It reveals the road you are on and who 
				you will become. The Life Path number is derived from your day, month, and year of birth.
			</p>
			<p>
			Birthday number: This number tells you about special skills you were born with and can now offer the world.
			</p>
			<p>
			Expression number: Your Expression number reveals your personal strengths and challenges that influence the way you walk your life path.
			</p>
			<p>
			Personality number: This is like the cover of your book -- it represents the side of you that you show to the world.
			</p>
			<p>
			Soul Urge number: Your Soul Urge points to your sincerest desires and what will make you feel fulfilled on the deepest level.
			</p>
			<p>
			Lucky Year, Month, and Day cycles: These numbers are always changing and reveal what you can expect during a given time frame.
			</p>
		</ul>
        <NumerologyList numerologies={numerologies} />
		</div>
    )
};

export default Numerology;