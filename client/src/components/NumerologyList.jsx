import React from 'react';
import NumerologyItem from './NumerologyItem';

// const NumerologyList = ({ numerologies }) => {
//     return (
//         <div >
//             <ul>
//                 {numerologies.map(numerology => (
//                     <NumerologyItem
//                         key={numerology.numerologyDescriptionId}
//                         numerology={numerology}
//                     />
//                 ))}
//             </ul>
//         </div>
//     );
// };

// export default NumerologyList;
const NumerologyList = ({ numerologies }) => {
    // Group numerologies by type
    const groupedNumerologies = numerologies.reduce((acc, numerology) => {
        if (!acc[numerology.numerologyType]) {
            acc[numerology.numerologyType] = [];
        }
        acc[numerology.numerologyType].push(numerology);
        return acc;
    }, {});

    return (
        <ul>
            {Object.entries(groupedNumerologies).map(([type, numerologyList]) => (
                <li key={type}>
                    <h3>{type}</h3>
                    {numerologyList.map((numerology, index) => (
                        <NumerologyItem key={index} numerology={numerology} />
                    ))}
                </li>
            ))}
        </ul>
    );
};

export default NumerologyList;