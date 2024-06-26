import React from 'react';
import { Link } from 'react-router-dom';
import NumerologyItem from './NumerologyItem';

const NumerologyList = ({ numerologies }) => {
    return (
        <div >
            <ul>
                {numerologies.map(numerology => (
                    <NumerologyItem
                        key={numerology.numerologyDescriptionId}
                        numerology={numerology}
                    />
                ))}
            </ul>
        </div>
    );
};

export default NumerologyList;