import React from 'react';

const ZodiacItem = ({ zodiac }) => {
    return (
        <li class="p-5"  >
            <h3 class="text-center">{zodiac.zodiacName}</h3>
            <img src="" alt="" />
            <p >{zodiac.zodiacDescription}</p>
        </li>
    );
};

export default ZodiacItem;
