import {useState, useEffect} from "react";
import { useNavigate, useLocation } from "react-router-dom"
import Errors from "../components/Errors";
import { jwtDecode } from "jwt-decode";

const LoginSignUp = ({setUser}) => {
    // const INITIAL_USER= {
    //     userId: 0,
    //     userName: "",
    //     password: "",
    //     email: "",
    //     firstName: "",
    //     middleName: "",
    //     lastName: "",
    //     dob: 0,
    //     zodiacId: 0,
    //     concordGroupId: 0,
    //   };

    // const setUser = props.setUser
    // const[user, setUser] = useState(INITIAL_USER);
    const [errors, setErrors] = useState([]);
    const [isLogin, setIsLogin] = useState(true);
    const [showSignupPrompt, setShowSignupPrompt] = useState(false);
    
    const[userName, setuserName] = useState("")
    const[password, setPassword] = useState("")
    const[email, setEmail] = useState("")
    const[firstName, setFirstName] = useState("")
    const[middleName, setMiddleName] = useState("")
    const[lastName, setLastName] = useState("")
    const[dob, setDob] = useState("")
    const[zodiacId, setZodiacId] = useState("")
    const[concordId, setConcordId] = useState("")
    
    // hide/show password
    const [showPassword, setShowPassword] = useState(false);
   
    const navigate = useNavigate();
    const location = useLocation();

    useEffect(() => {
        if (location.pathname === '/signup') {
            setIsLogin(false);
        } else {
            setIsLogin(true);
        }
    }, [location.pathname]);

    function signUp() {
        fetch("http://localhost:8080/api/users", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json"
            },
            body: JSON.stringify({userName,password,email,firstName,middleName,lastName,dob})
        })
        .then(response => {
            if (response.ok) {
                response.json().then(json => {
                    // setUser(json); 
                    // localStorage.setItem("user", JSON.stringify(json));
                    const userObject = jwtDecode(json.jwt)
                    userObject.jwt = json.jwt
                    setUser(userObject)
                    localStorage.setItem("users", JSON.stringify(userObject))
                    // navigate("/");
                });
                // console.log("Success!");
                // navigate("/")
            } else if (response.status >= 400 || response.status <= 499) {
                response.json()
                .then(json => setErrors(json))
            } else {
                return Promise.reject()
            }
        })
        .catch(() => {
            setErrors(["Something went wrong"])
        })
    }

    function logIn() {
        fetch("http://localhost:8080/api/users/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json"
            },
            body: JSON.stringify({userName,password})
        })
        .then(response => {
            if (response.ok) {
                response.json().then(json => {
                    const decodedToken = jwtDecode(json.jwt);
                    const userObject = {
                        jwt: json.jwt,
                        userName: decodedToken.user_name,
                        userId: decodedToken.user_id,
                        zodiacId: decodedToken.zodiac_id,
                        concordGroupId: decodedToken.concord_group_id
                    };
                    // userObject.jwt = json.jwt
                    // userObject.zodiacId = json.zodiacId; 
                    // userObject.concordGroupId = json.concordGroupId;
                    // userObject.userId = json.userId;
                    setUser(userObject)
                    localStorage.setItem("users", JSON.stringify(userObject))
                    navigate("/luckyme");
                });
            } else if (response.status === 404 || response.status === 403) {
                response.json()
                .then(json => setErrors(json));
                setShowSignupPrompt(true); 
            } else {
                return Promise.reject()
            }
        })
        .catch(() => {
            setErrors(["Something went wrong during login"]);
        })
    }

    function handleSubmit(event) {
        event.preventDefault();
        // logIn();
        if (isLogin) {
          logIn();
        } else {   
          signUp();
        }
      }

      function switchToSignUp() {
        navigate('/signup');
    }

      function togglePasswordVisibility() {
        setShowPassword(prevShowPassword => !prevShowPassword);
    }

    return (
        <div>
            <h3>{isLogin ? 'Login' : 'Sign Up'}</h3>
            <Errors errors={errors} />
            <form onSubmit={handleSubmit}>
                <fieldset>
                    <div>
                        <label htmlFor="userName-input">Username</label>
                        <input id="userName-input" name="userName" type="text" value={userName}
                        onChange={event => setuserName(event.target.value)}  className="mb-3"/>    
                    </div>
                    <div>
                        <label htmlFor="password-input">Password</label>
                        <input id="password-input" name="password" type={showPassword ? "text" : "password"} value={password}
                        onChange={event => setPassword(event.target.value)}  className="mb-3" />
                        <button type="button" onClick={togglePasswordVisibility}>
                            {showPassword ? "Hide" : "Show"}
                        </button> 
                    </div>
                    {!isLogin && (
                        <>
                            <div>
                                
                                <label htmlFor="email-input">Email</label>
                                <input id="email-input" name="email" type="text" value={email}
                                onChange={event => setEmail(event.target.value)}  className="mb-3" />
                            </div>
                            <div>
                                
                                <label htmlFor="firstName-input">First Name</label>
                                <input id="firstName-input" name="firstName" type="text" value={firstName}
                                onChange={event => setFirstName(event.target.value)}  className="mb-3" />
                            </div>

                            <div>
                                <label htmlFor="middleName-input">Middle Name</label>
                                <input id="middleName-input" name="middleName" type="text" value={middleName}
                                onChange={event => setMiddleName(event.target.value)}  className="mb-3" />
                            </div>

                            <div>
                                <label htmlFor="lastName-input">Last Name</label>
                                <input id="lastName-input" name="lastName" type="text" value={lastName}
                                onChange={event => setLastName(event.target.value)}  className="mb-3" />
                            </div>

                            <div className='col-12 col-md-4 mb-3'>
                            <label htmlFor='dob' className='form-label'>
                                Date Of Birth
                            </label>
                            <input
                                className='form-control' type='date' id='dob' name='dob' value={dob}
                                onChange={event => setDob(event.target.value)} />
                            </div>
                        </>
                    )}
                    <button type="submit">{isLogin ? 'Login' : 'Sign Up'}</button>
                
                </fieldset>    
            </form>
            {showSignupPrompt && isLogin && (
                <div>
                    <h4>Would you like to sign up</h4>
                    <button type="button" onClick={switchToSignUp}>Yes</button>
                    <button type="button" onClick={() => setShowSignupPrompt(false)}>No</button>
            </div>
            )} 

        </div>
    )

}

export default LoginSignUp