import React from 'react';

const ZodiacItem = ({ zodiac }) => {
    return (
        <li>
            <h3>{zodiac.zodiacName}</h3>
            <p>{zodiac.zodiacDescription}</p>
        </li>
    );
};

export default ZodiacItem;
