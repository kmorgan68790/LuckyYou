import React from 'react';

const NumerologyItem = ({ numerology }) => {
    return (
        <li>
            {/* <h3>{numerology.numerologyType}</h3> */}
            <h5>Numerology Number: {numerology.numerologyNumber}</h5>
            <p className='mb-4'>Description: {numerology.numerologyDescription}</p>
        </li>
    );
};

export default NumerologyItem;