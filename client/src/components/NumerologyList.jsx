import React from 'react';
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