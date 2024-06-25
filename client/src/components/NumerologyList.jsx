import React from 'react';
import { Link } from 'react-router-dom';
import NumerologyItem from './NumerologyItem';

const NumerologyList = ({ numerologies, user }) => {
    // return (
    //     <ul>
    //         {numerologies && numerologies.map(numerology => (
    //                 <NumerologyItem
    //                 key={numerology.numerologyDescriptionId} numerology={numerology} user={user}/>
    //             ))}
    //     </ul>
    // );
    const groupedNumerologies = numerologies.reduce((acc, item) => {
        if (!acc[item.numerologyType]) {
            acc[item.numerologyType] = [];
        }
        acc[item.numerologyType].push(item);
        return acc;
    }, {});

    return (
        <div>
            {Object.keys(groupedNumerologies).map(type => (
                <div key={type}>
                    <h3>{type}</h3>
                    <ul>
                        {groupedNumerologies[type].map(numerology => (
                            <NumerologyItem
                                key={numerology.numerologyDescriptionId}
                                numerology={numerology}
                            />
                        ))}
                    </ul>
                </div>
            ))}
        </div>
    );
};

export default NumerologyList;