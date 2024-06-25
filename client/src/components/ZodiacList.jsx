import React from 'react';
import { Link } from 'react-router-dom';
import ZodiacItem from './ZodiacItem';

const ZodiacList = ({ zodiacs, user }) => {
    return (
        <ul>
            {zodiacs && zodiacs.map(zodiac => (
                    <ZodiacItem
                    key={zodiac.zodiacId} zodiac={zodiac} user={user}/>
                ))}
        </ul>
    );
};

export default ZodiacList;

