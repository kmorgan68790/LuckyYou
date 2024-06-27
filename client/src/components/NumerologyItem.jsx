import React from 'react';

const NumerologyItem = ({ numerology }) => {
    return (
        <li>
            <h3>{numerology.numerologyType}</h3>
            <p>Numerology Number: {numerology.numerologyNumber}</p>
            <p>Description: {numerology.numerologyDescription}</p>
        </li>
    );
};

export default NumerologyItem;