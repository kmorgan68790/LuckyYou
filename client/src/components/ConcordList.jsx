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
        <div className='concord mx-5' >
            <p>Every person is born with a vibration, and that vibration depends on the day of birth. The day of birth determines
                which vibrational group a person belongs to. There are three groups and any dates in the group that a person belongs to is 
                good for them. The dates for group one are 1,5,7 and any birthdates that reduce to these numbers, for group two the dates are 
                2, 4, 8 and any dates that reduce to these numbers, and the same rules apply for group three whose dates are 3, 6, 9. These three 
                groups are what's known as concord groups, and within them they can be further divided into mental, physical, and spiritual days. To 
                find which days are the luckiest for you simply find the day you were born in the groups below.
            </p>
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
                                            <ul className='mx-4'>
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
        </div>
    );
};

export default ConcordList;