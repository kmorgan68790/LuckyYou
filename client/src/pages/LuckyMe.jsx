import { useEffect, useState } from "react";
import NumerologyList from "../components/NumerologyList";
import ZodiacItem from "../components/ZodiacItem";

const LuckyMe = ({ user }) => {
    const [numerologies, setNumerologies] = useState([]);
    const [zodiac, setZodiac] = useState([]);
    const [concordGroup, setConcordGroup] = useState(null);
    const [concordDays, setConcordDays] = useState([]);

    useEffect(() => {
        if (user && user.userId) {
            setNumerologies([]); // Reset numerologies state
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

        const fetchConcordGroup = (concordGroupId) => {
            fetch(`http://localhost:8080/api/concord-group/id/${concordGroupId}`, {
                headers: {
                    Authorization: `Bearer ${user.jwt}`
                }
            })
            .then(response => response.json())
            .then(json => {
                setConcordGroup(json);
                const birthdayNumber = user.dob;
                fetchConcordDays(user.dob, concordGroupId);
            })
            .catch(err => console.error("Failed to fetch concord group data", err));
        };

        const fetchConcordDays = (birthdayNumber, concordGroupId) => {
            console.log(birthdayNumber)
            console.log(concordGroupId)
            fetch(`http://localhost:8080/api/concord-days/birthday/${birthdayNumber}/group/${concordGroupId}`, {
                headers: {
                    Authorization: `Bearer ${user.jwt}`
                }
            })
            .then(response => response.json())
            .then(json => {
                console.log(json)
                const filteredDays = json.filter(day => day.concordBirthdayNumber === birthdayNumber);
                setConcordDays(filteredDays);
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
            <div >
                <div class="luckyMeContainer">
                    <h1 >LuckyMe</h1>
                    <p>Welcome, to your personal LuckyMe page. Here you will find your lucky numbers, zodiac sign, and lucky days. Good Luck!</p>
                </div>
                 {zodiac && (
                <div className="luckyMeContainer" >
                    <ZodiacItem zodiac={zodiac} />
                </div>
            )}
                {concordGroup && (
                    <div> 
                        <div class="luckyMeContainer">
                            <h2>Concord Group {concordGroup.concordGroupNumber}</h2>
                            <p>{concordGroup.concordGroupDescription}</p>
                        </div>
                        {Object.entries(groupDaysByType(concordDays)).map(([type, days]) => (
                            <div key={type } class="luckyMeContainer">
                                <h3>{type} days:</h3>
                                <p>{days.join(', ')}</p>
                            </div>
                        ))}
                    </div>
                )}
                <div class="luckyMeContainer"> 
                    <NumerologyList key={user.userId} numerologies={numerologies} />
                </div>
                
            </div>
        );
    };

    export default LuckyMe;
