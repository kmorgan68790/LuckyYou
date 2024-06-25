import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Errors from "../components/Errors";

const LuckyMe = ({ user }) => {
    const navigate = useNavigate();
    const [userData, setUserData] = useState(null);
    const [userNumerologyMapping, setUserNumerologyMapping] = useState([]);
    const [errors, setErrors] = useState([]);

    useEffect(() => {
        if (!user) {
            setErrors(["User is not logged in."]);
            return;
        }

        const { zodiacId, concordGroupId, userId, jwt, userMapping } = user;

        if (!zodiacId || !concordGroupId || !userId || !jwt) {
            setErrors(["User data is incomplete."]);
            return;
        }

            const fetchZodiacInfo = fetch(`http://localhost:8080/api/zodiac/id/${zodiacId}`, {
                headers: {
                    Authorization: `Bearer ${jwt}`
                }
            }).then(response => response.json());

            const fetchConcordInfo = fetch(`http://localhost:8080/api/concord-group/id/${user.concordGroupId}`, {
                headers: {
                    Authorization: `Bearer ${user.jwt}`
                }
            }).then(response => response.json());

            const fetchNumerologyInfo = fetch(`http://localhost:8080/api/mapping/user/${user.userId}`, {
                headers: {
                    Authorization: `Bearer ${user.jwt}`
                }
            }).then(response => response.json());

             // Fetch User Numerology Mapping Info
            const fetchUserNumerologyMapping = fetch(`http://localhost:8080/api/mapping/user/${userId}`, {
            headers: {
                Authorization: `Bearer ${jwt}`
            }
            }).then(response => response.json());

            Promise.all([fetchZodiacInfo, fetchConcordInfo, fetchNumerologyInfo, fetchUserNumerologyMapping])
                .then(([zodiacInfo, concordInfo, numerologyInfo, userNumerologyMappingInfo]) => {
                    setUserData({
                        zodiac: zodiacInfo,
                        concord: concordInfo,
                        mapping: numerologyInfo,
                        userNumerologyMapping: userNumerologyMappingInfo
                    });
                    setUserNumerologyMapping(userMapping);
                })
                .catch(err => setErrors(["Failed to fetch user data"]));
        
    }, [user]);

    if (!user) {
        return <div>Please log in to view your personalized information.</div>;
    }

    return (
        <div>
            <h3>Lucky Me</h3>
            <Errors errors={errors} />
            {userData && (
                <div>
                    <h4>Zodiac Information</h4>
                    <p>Sign: {userData.zodiac.zodiacName}</p>
                    <p>Description: {userData.zodiac.zodiacDescription}</p>

                    <h4>Concord Information</h4>
                    <p>Concord Group: {userData.concord.concordGroupNumber}</p>
                    <p>Description: {userData.concord.description}</p>

                    <h4>Numerology Information</h4>
                    <p>Life Path Number: {userData.mapping.numerologyType}</p>
                    <p>Birthday Number: {userData.mapping.birthdayNumber}</p>
                    <p>Expression Number: {userData.mapping.expressionNumber}</p>
                    <p>Personality Number: {userData.mapping.personalityNumber}</p>
                    <p>Soul Urge Number: {userData.mapping.soulUrgeNumber}</p>

                     <h4>User Numerology Mapping Information</h4>
                    {userData.userNumerologyMapping && userData.userNumerologyMapping.map(mapping => (
                        <div key={mapping.user_numerology_mapping_id}>
                            <p>Numerology Type: {mapping.numerology_type}</p>
                            {/* Display other relevant fields from user numerology mapping */}
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default LuckyMe;