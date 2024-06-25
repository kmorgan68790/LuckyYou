import React from 'react';

const ConcordDaysItem = ({ concordDay }) => {
    return (
        <li>
            <h3>{concordDay.dayType}</h3>
            <p>{concordDay.concordDayNumber}</p>
        </li>
    );
};

export default ConcordDaysItem;