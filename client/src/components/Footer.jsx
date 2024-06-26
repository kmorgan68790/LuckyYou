import NavLink from "./NavLink"

const Footer = ({setUser, user}) => {
    const logOutLink = <li className='nav-item'>
        <span className='nav-link' onClick={() => {
            setUser(null)
            localStorage.removeItem("user")
            }}>
            Log Out
        </span>
    </li>

    return (
        <nav className='navbar navbar-expand justify-content-center'>
        <ul className="navbar-nav" >
            {/* <li><h2>LuckyYou</h2></li>
            <li>
                <img src={clover} alt='Four leaf clover' height='45'/>
            </li> */}
                
            {/* always visible */}
            <NavLink name="About" to="/about"/>
            <NavLink name="Contact" to="/contact"/>
            <NavLink name="News Letter" to="/"/>
            <NavLink name="Privacy" to="/"/>
        </ul>
        </nav>
    )
}

export default Footer