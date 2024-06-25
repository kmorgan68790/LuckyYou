import React from 'react';
import { Link } from 'react-router-dom';
import NumerologyItem from './NumerologyItem';

const NumerologyList = ({ numerologies }) => {
    return (
        <ul>
            {numerologies.map(numerology => (
                <NumerologyItem
                    key={numerology.numerologyDescriptionId}
                    numerology={numerology}
                />
            ))}
        </ul>
    );
};

export default NumerologyList;