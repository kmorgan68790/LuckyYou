import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Errors from "../components/Errors";

const Edit = ({ user, setUser }) => {
    const navigate = useNavigate();

    const [userData, setUserData] = useState({
        userId: user ? user.userId : 0,
        userName: "",
        email: "",
        firstName: "",
        middleName: "",
        lastName: "",
        password: ""
    });

    const [errors, setErrors] = useState([]);

    useEffect(() => {
        if (user && user.userId > 0) {
            fetch(`http://localhost:8080/api/users/id/${user.userId}`, {
                headers: {
                    Authorization: `Bearer ${user.jwt}`
                }
            })
            .then(response => response.json())
            .then(json => setUserData(json))
            .catch(err => setErrors(["Failed to fetch user data"]));
        }
    }, [user]);

    const handleChange = (event) => {
        const { name, value } = event.target;
        setUserData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        fetch(`http://localhost:8080/api/users/${user.userId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json",
                Authorization: `Bearer ${user.jwt}`,
            },
            body: JSON.stringify(userData)
        })
        .then(response => {
            if (response.ok) {
                navigate("/luckyme");
            } else if (response.status === 403) {
                setUser(null);
                localStorage.removeItem("users");
            } else if (response.status >= 400 && response.status < 499) {
                response.json().then(json => setErrors(json));
            } else {
                return Promise.reject();
            }
        })
        .catch(err => {
            setErrors(["Something went wrong!"]);
        });
    };

    return (
        <>
            <h3>Edit Your Profile</h3>
            <Errors errors={errors} />
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="userName">Username</label>
                    <input
                        type="text"
                        id="userName"
                        name="userName"
                        value={userData.userName}
                        onChange={handleChange}
                        className="mb-3"
                    />
                </div>
                <div>
                    <label htmlFor="email">Email</label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        value={userData.email}
                        onChange={handleChange}
                        className="mb-3"
                    />
                </div>
                <div>
                    <label htmlFor="firstName">First Name</label>
                    <input
                        type="text"
                        id="firstName"
                        name="firstName"
                        value={userData.firstName}
                        onChange={handleChange}
                        className="mb-3"
                    />
                </div>
                <div>
                    <label htmlFor="middleName">Middle Name</label>
                    <input
                        type="text"
                        id="middleName"
                        name="middleName"
                        value={userData.middleName}
                        onChange={handleChange}
                        className="mb-3"
                    />
                </div>
                <div>
                    <label htmlFor="lastName">Last Name</label>
                    <input
                        type="text"
                        id="lastName"
                        name="lastName"
                        value={userData.lastName}
                        onChange={handleChange}
                        className="mb-3"
                    />
                </div>
                <div>
                    <label htmlFor="password">Password</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        value={userData.password}
                        onChange={handleChange}
                        className="mb-3"
                    />
                </div>
                <button type="submit">Save Changes</button>
            </form>
        </>
    );
};

export default Edit;
