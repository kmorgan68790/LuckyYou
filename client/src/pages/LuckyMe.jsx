import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Errors from "../components/Errors";
import NumerologyList from "../components/NumerologyList";
import ConcordList from "../components/ConcordList";
import ZodiacList from "../components/ZodiacList";
import ConcordDaysItem from "../components/ConcordDaysItem";
import ConcordItem from "../components/ConcordItem";

const LuckyMe = ({ user }) => {
    const [numerologies, setNumerologies] = useState([]);
    const [zodiac, setZodiac] = useState([]);
    const [concordGroup, setConcordGroup] = useState(null);
    const [concordDays, setConcordDays] = useState([]);
    const [filteredConcordDays, setFilteredConcordDays] = useState([]);

    useEffect(() => {
        if (user && user.userId) {
            fetchNumerologies(user.userId);
            fetchZodiac(user.zodiacId);
            fetchConcordGroup(user.concordGroupId);
        }
    }, [user]);

    const fetchNumerologies = (userId) => {
        fetch(`http://localhost:8080/api/mapping/user/${userId}`, {
            headers: {
                Authorization: `Bearer ${user.jwt}`
            }
        })
        .then(response => response.json())
        .then(json => {
            // Assuming json contains data like { numerology_type, numerology_description_id }
            // Fetch numerology details for each numerology_description_id
            const promises = json.map(mapping => {
                return fetch(`http://localhost:8080/api/numerology/id/${mapping.numerologyDescriptionId}`)
                        .then(response => response.json());
            });

            Promise.all(promises)
            .then(numerologyDetails => {
                const formattedNumerologies = json.map((mapping, index) => ({
                    numerologyType: mapping.numerologyType,
                    numerologyNumber: numerologyDetails[index].numerologyNumber,
                    numerologyDescription: numerologyDetails[index].numerologyDescription
                }));
                setNumerologies(formattedNumerologies);
            })
            .catch(err => console.error("Failed to fetch numerology details", err));
        })
        .catch(err => console.error("Failed to fetch numerology data", err));
    };

    const fetchZodiac = (zodiacId) => {
        fetch(`http://localhost:8080/api/zodiac/id/${zodiacId}`, {
            headers: {
                Authorization: `Bearer ${user.jwt}`
            }
        })
        .then(response => response.json())
        .then(json => setZodiac(json))
        .catch(err => console.error("Failed to fetch zodiac data", err));
    };

//     const fetchConcordGroup = (concordGroupId) => {
//         fetch(`http://localhost:8080/api/concord-group/id/${concordGroupId}`, {
//             headers: {
//                 Authorization: `Bearer ${user.jwt}`
//             }
//         })
//         .then(response => response.json())
//         .then(json => {
//             setConcordGroup(json);
//             const birthdayNumber = new Date(user.dob).getDate();
//             fetchConcordDays(user.userId, concordGroupId);
//         })
//         .catch(err => console.error("Failed to fetch concord group data", err));
//     };

//     const fetchConcordDays = (birthdayNumber, concordGroupId) => {
//         fetch(`http://localhost:8080/api/concord-days/group/${concordGroupId}`, {
//             headers: {
//                 Authorization: `Bearer ${user.jwt}`
//             }
//         })
//         .then(response => response.json())
//         .then(json => {
//             const filteredDays = json.filter(day => day.concordBirthdayNumber === birthdayNumber);
//             setConcordDays(filteredDays);
//         })
//         .catch(err => console.error("Failed to fetch concord days data", err));
//     };

//     const groupDaysByType = (days) => {
//         return days.reduce((acc, day) => {
//             if (!acc[day.dayType]) {
//                 acc[day.dayType] = [];
//             }
//             acc[day.dayType].push(day.concordDayNumber);
//             return acc;
//         }, {});
//     };

//     return (
//         <div >
//             <div class="luckyMeContainer">
//                 <h1 >LuckyMe</h1>
//                 <p>Welcome, to your personal LuckyMe page. Here you will find your lucky numbers, zodiac sign, and lucky days. Good Luck!</p>
//             </div>
//             {zodiac && (
//                 <div class="luckyMeContainer">
//                     <h2>{zodiac.zodiacName}</h2>
//                     <p>{zodiac.zodiacDescription}</p>
//                 </div>
//             )}

//             {concordGroup && (
//                 <div> 
//                     <div class="luckyMeContainer">
//                         <h2>Concord Group {concordGroup.concordGroupNumber}</h2>
//                         <p>{concordGroup.concordGroupDescription}</p>
//                     </div>
//                     {Object.entries(groupDaysByType(concordDays)).map(([type, days]) => (
//                         <div key={type} class="luckyMeContainer">
//                             <h3>{type} days:</h3>
//                             <p>{days.join(', ')}</p>
//                         </div>
//                     ))}
//                 </div>
//             )}

//             <div class="luckyMeContainer p-0"> 
//                 <NumerologyList numerologies={numerologies} />
//             </div>
            
//         </div>
//     );
// };

// export default LuckyMe;
const fetchConcordGroup = (concordGroupId) => {
    fetch(`http://localhost:8080/api/concord-group/id/${concordGroupId}`, {
        headers: {
            Authorization: `Bearer ${user.jwt}`
        }
    })
    .then(response => response.json())
    .then(groupData => {
        setConcordGroup(groupData);
        fetchConcordDays(groupData.concordGroupId, user.dob);
    })
    .catch(err => console.error("Failed to fetch concord group data", err));
};

const fetchConcordDays = (concordGroupId, dob) => {
    const birthdayNumber = new Date(dob).getDate();  // Extract the day part of the date
    fetch(`http://localhost:8080/api/concord-days/group/${concordGroupId}`, {
        headers: {
            Authorization: `Bearer ${user.jwt}`
        }
    })
    .then(response => response.json())
    .then(daysData => {
        const filteredDays = daysData.filter(day => day.concordBirthdayNumber === birthdayNumber);
        setFilteredConcordDays(filteredDays);
    })
    .catch(err => console.error("Failed to fetch concord days data", err));
};

const groupDaysByType = (days) => {
    return days.reduce((acc, day) => {
        if (!acc[day.dayType]) {
            acc[day.dayType] = [];
        }
        acc[day.dayType].push(day.concordDayNumber);
        return acc;
    }, {});
};

return (
    <div>
        <div className="luckyMeContainer">
            <h1>LuckyMe</h1>
            <p>Welcome, to your personal LuckyMe page. Here you will find your lucky numbers, zodiac sign, and lucky days. Good Luck!</p>
        </div>
        {zodiac && (
            <div className="luckyMeContainer">
                <h2>{zodiac.zodiacName}</h2>
                <p>{zodiac.zodiacDescription}</p>
            </div>
        )}
        {concordGroup && (
            <div>
                <div className="luckyMeContainer">
                    <h2>Concord Group {concordGroup.concordGroupNumber}</h2>
                    <p>{concordGroup.concordGroupDescription}</p>
                </div>
                {Object.entries(groupDaysByType(filteredConcordDays)).map(([type, days]) => (
                    <div key={type} className="luckyMeContainer">
                        <h3>{type} days:</h3>
                        <p>{days.join(', ')}</p>
                    </div>
                ))}
            </div>
        )}
        <div className="luckyMeContainer p-0">
            <NumerologyList numerologies={numerologies} />
        </div>
    </div>
);
};

export default LuckyMe;