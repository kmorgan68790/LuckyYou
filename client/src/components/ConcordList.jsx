import React from 'react';
import { Link } from 'react-router-dom';
import ConcordItem from './ConcordItem';
import ConcordDaysItem from './ConcordDaysItem';

const concordList = ({ concords, concordDays, user }) => {
    return (
        <ul>
            {concords && concords.map(concord => (
                    <ConcordItem
                    key={concord.concordGroupId} concord={concord} user={user}/>
                ))}
            {concordDays && concordDays.map(concordDay => (
                    <ConcordDaysItem
                    key={concordDay.concordGroupId} concordDay={concordDay} user={user}/>
                ))}    
        </ul>
    );
};

export default concordList;