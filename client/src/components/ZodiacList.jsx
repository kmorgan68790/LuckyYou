import React from 'react';
import { Link } from 'react-router-dom';
import ZodiacItem from './ZodiacItem';

const ZodiacList = ({ zodiacs, user }) => {
    return (
        <div>
            <ul>
                <h1 className="mx-5 mb-3">Zodiac Signs: "It's written in the stars" </h1>
                <p className="mx-5 mb-3">From fiery Aries to compassionate Pisces, each zodiac sign possesses unique qualities that shape an individual’s personality. 
                    A deeper understanding of these characteristics can help shed light on how luck may manifest for each sign. The positions of 
                    the planets at the time of birth play a significant role in determining an individual’s luck. Each planet represents different 
                    energies and influences, and their relationships with one another can create a complex web of energies that shape an individual’s 
                    life experiences. In astrology, certain planets are considered the significators of luck. Jupiter, often known as the “planet of luck,” 
                    is associated with expansion, abundance, and opportunities. Other benefic planets, such as Venus and the Moon, also play a role in shaping 
                    an individual’s luck.
                </p>
                {zodiacs && zodiacs.map(zodiac => (
                        <ZodiacItem
                        key={zodiac.zodiacId} zodiac={zodiac} user={user}/>
                    ))}
            </ul>
        </div>
    );
};

export default ZodiacList;

