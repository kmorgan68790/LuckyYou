import React from 'react';

const NumerologyItem = ({ numerology }) => {

    return (
        <li>
            {/* <h3>{numerology.numerologyType}</h3> */}
            <p>{numerology.numerologyNumber}</p>
            <p>{numerology.numerologyDescription}</p>
        </li>
    );
};

export default NumerologyItem;