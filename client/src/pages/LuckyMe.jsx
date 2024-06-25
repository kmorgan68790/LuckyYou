import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Errors from "../components/Errors";
import NumerologyList from "../components/NumerologyList";

const LuckyMe = ({ user }) => {
    const [numerologies, setNumerologies] = useState([]);

    useEffect(() => {
        if (user && user.userId) {
            fetchNumerologies(user.userId);
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

    return (
        <div>
            <h1>Lucky Me</h1>
            <NumerologyList numerologies={numerologies} />
        </div>
    );
};

export default LuckyMe;