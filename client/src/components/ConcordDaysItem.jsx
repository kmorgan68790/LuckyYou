import React from 'react';

const ConcordDaysItem = ({ concordDay }) => {
    return (
        <li>
            <p>{concordDay.concordDayNumber}</p>
        </li>
    );
};

export default ConcordDaysItem;