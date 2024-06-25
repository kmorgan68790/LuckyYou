import React from 'react';
import { Link } from 'react-router-dom';
import ConcordItem from './ConcordItem';
import ConcordDaysItem from './ConcordDaysItem';

// export default ConcordList;
const ConcordList = ({ concords, concordDays, user }) => {
    // Group concords by concordGroupId
    const groupedConcords = concords.reduce((acc, concord) => {
        if (!acc[concord.concordGroupId]) {
            acc[concord.concordGroupId] = [];
        }
        acc[concord.concordGroupId].push(concord);
        return acc;
    }, {});

    // Group concord days by birthday number and then by day type
    const groupDaysByBirthday = (days) => {
        const result = {};
        days.forEach(day => {
            if (!result[day.concordBirthdayNumber]) {
                result[day.concordBirthdayNumber] = {};
            }
            if (!result[day.concordBirthdayNumber][day.dayType]) {
                result[day.concordBirthdayNumber][day.dayType] = [];
            }
            result[day.concordBirthdayNumber][day.dayType].push(day);
        });
        return result;
    };

    return (
        <ul>
            {Object.keys(groupedConcords).map(concordGroupId => (
                <li key={concordGroupId}>
                    <h3>Group {concordGroupId}:</h3>
                    <p>{groupedConcords[concordGroupId][0].concordDescription}</p>
                    <ul>
                        {Object.entries(groupDaysByBirthday(concordDays[concordGroupId] || [])).map(([birthdayNumber, dayTypes]) => (
                            <li key={birthdayNumber}>
                                <h4>Born on {birthdayNumber} of any month</h4>
                                {Object.entries(dayTypes).map(([dayType, days]) => (
                                    <div key={dayType}>
                                        <h5>{dayType} days:</h5>
                                        <ul>
                                            {days.map(day => (
                                                <ConcordDaysItem
                                                    key={day.concordDaysId}
                                                    concordDay={day}
                                                />
                                            ))}
                                        </ul>
                                    </div>
                                ))}
                            </li>
                        ))}
                    </ul>
                </li>
            ))}
        </ul>
    );
};

export default ConcordList;