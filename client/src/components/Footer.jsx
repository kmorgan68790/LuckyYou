import NavLink from "./NavLink"

const Footer = () => {
    return (
        <nav className='navbar navbar-expand justify-content-center'>
        <ul className="navbar-nav" >         
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