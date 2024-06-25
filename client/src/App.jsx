import { useEffect, useState } from 'react';
import NavBar from './components/NavBar';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Home from './pages/Home'
import LoginSignUp from './pages/LoginSignUp';
import Zodiac from "./pages/Zodiac"
import LuckyMe from './pages/LuckyMe'
import Edit from './pages/Edit';
import Concord from './pages/Concord';
import Numerology from './pages/Numerology';
import Footer from './components/Footer';
import About from './pages/About'
import clover from '././assets/clover.png';


function App() {
  const initialUser = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null
	const [user, setUser] = useState(initialUser)
	const navigateFromProtectedRoute = <Navigate to="/"/>;

  return (
    <BrowserRouter>
    <div className='container'>
				<header className='mb-3'>
					<nav className='navbar navbar-expand'>
						<div className='d-flex' >
						<h2>LuckyYou</h2>
						<a className='navbar-brand' href='/'>
						<img src={clover} alt='Four leaf clover' height='45'/>
							</a>
							<NavBar setUser={setUser} user={user}/>
						</div>
					</nav>
				</header>
				<main>
					{/* <h2 className='mb-3'>Lucky You</h2>
					{user !== null ? <h3>Welcome, {user.username}</h3> : null} */}
					<Routes>
						<Route path="/" element={<Home />} />
						{/* <Route path="/contact" element={<Contact />} /> */}
						{/* <Route path="/allPanels" element={<AllPanels user={user}/>} /> */}
						<Route path="/zodiac" element={<Zodiac user={user}/>} />
						<Route path="/concord" element={<Concord user={user}/>} />
						<Route path="/numerology" element={<Numerology user={user}/>} />
						<Route path="/about" element={<About user={user}/>} />

						<Route path="/luckyme" element={user ?
                            <LuckyMe user={user}/>: navigateFromProtectedRoute} />

						<Route path="/edit/:userId" element={user ?
                            <Edit user={user}/>: navigateFromProtectedRoute} />				
						{/* <Route path="/delete/:id" element={user ?
                            <ConfirmDelete user={user}/>: navigateFromProtectedRoute} /> */}

						<Route path ="/login" element={!user ? <LoginSignUp setUser={setUser} /> : navigateFromProtectedRoute} />
						<Route path ="/signup" element={!user ? <LoginSignUp setUser={setUser} /> : navigateFromProtectedRoute} />
						<Route path="*" element={<div>Page not found, 404</div>} />
					</Routes>
				</main>
				<footer>
					<nav className='navbar navbar-expand'>
						<div className='d-flex'>
							{/* <a className='navbar-brand' href='/'>
								<img src={logo} alt='Solar Farm' width='150' />
							</a> */}
							<Footer setUser={setUser} user={user}/>
						</div>
					</nav>
				</footer>
			</div>
 
    </BrowserRouter>
  )
}

export default App
