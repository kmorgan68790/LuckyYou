import {useState, useEffect} from "react";
import { useNavigate, useLocation } from "react-router-dom"
import Errors from "../components/Errors";

const Home = () => {
    // const [errors, setErrors] = useState([]);

    return (
    <div>
        <h1>Welcome to LuckyYou</h1>
        {/* <Errors errors={errors} />
        <form onSubmit={handleSubmit}>
            <fieldset>
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
            
            </fieldset> 
            </form>   */}

        </div>
    )
}

export default Home