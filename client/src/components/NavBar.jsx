import NavLink from "./NavLink"

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
        <ul className='navbar-nav'>
            {/* always visible */}
            <NavLink name="Home" to="/"/>
            <NavLink name="Zodiac" to="/numerology"/>
            <NavLink name="Numerology" to="/numerology"/>
            <NavLink name="About" to="/contact"/>
            <NavLink name="Contact" to="/contact"/>
            {/* TODO: refactor routes into /solarPanel/add and /user/add */}

            {/* visible only when logged out */}
            { user === null && <NavLink name="Sign Up" to="/signup" /> }
            { user === null && <NavLink name="Log In" to="/login" /> }
                        
            {/* visible only when logged in */}
            { user !== null && <NavLink name="Add" to="/add" /> }
            { user !== null && <NavLink name="Lucky Me" to="/personal" /> }
            { user !== null && logOutLink }
        </ul>
    )
}

export default NavBar