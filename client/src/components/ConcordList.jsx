import React from 'react';
import { Link } from 'react-router-dom';
import ConcordItem from './ConcordItem';
import ConcordDaysItem from './ConcordDaysItem';

const concordList = ({ concords, concordDays, user }) => {
    // leti = 0
    console.log(Object.keys(concordDays))
    return (
        <ul>
            {concords && concords.map(concord => (
                    <ConcordItem
                    key={concord.concordGroupId} concord={concord} user={user}/>
                ))}
            {/* {concordDays && concordDays.map(concordDay => (
                    <ConcordDaysItem
                    key={concordDay.concordGroupId} concordDay={concordDay} user={user}/>
                ))}     */}
            {Object.keys(concordDays).map(concordGroupId => <div>{concordDays[concordGroupId]
            .map(concordDay => (
                <ConcordDaysItem
                key={concordDay.concordDaysId} concordDay={concordDay} user={user}/>
            ))}</div>)}
        </ul>
    );
};

export default concordList;