import NavLink from "./NavLink"
import clover from '.././assets/clover.png';

const NavBar = ({setUser, user}) => {
    const logOutLink = <li className='nav-item'>
        <span className='nav-link' onClick={() => {
            setUser(null)
            localStorage.removeItem("user")
            }}>
            Log Out
        </span>
    </li>

    return (
        <nav className='navbar navbar-expand justify-content-between'>
        <h2>LuckyYou</h2>
        <img src={clover} alt='Four leaf clover' height='45'/>
        <ul className='navbar-nav justify-content-space-between'>
                
            {/* always visible */}
            <NavLink name="Home" to="/"/>
            <NavLink name="Zodiac" to="/zodiac"/>
            <NavLink name="Concord" to="/concord"/>
            <NavLink name="Numerology" to="/numerology"/>
            <NavLink name="About" to="/about"/>
            <NavLink name="Contact" to="/contact"/>
            {/* TODO: refactor routes into /solarPanel/add and /user/add */}

            {/* visible only when logged out */}
            { user === null && <NavLink name="Sign Up" to="/signup" /> }
            { user === null && <NavLink name="Log In" to="/login" /> }
                        
            {/* visible only when logged in */}
            {/* { user !== null && <NavLink name="Add" to="/add" /> } */}
            { user !== null && <NavLink name="Lucky Me" to="/luckyme" /> }
            { user !== null && <NavLink name="Edit" to={`/edit/${user.userId}`} /> }
            { user !== null && logOutLink }
        </ul>
        </nav>
    )
}

export default NavBar