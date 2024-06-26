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

function App() {
  const initialUser = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null
	const [user, setUser] = useState(initialUser)
	const navigateFromProtectedRoute = <Navigate to="/"/>;

  return (
    <BrowserRouter>
    <div>
		<header className='mb-3  bg-success text-white'>				
					<NavBar setUser={setUser} user={user}/>
		</header>
		<main>
			<Routes>
				<Route path="/" element={<Home />} />
				<Route path="/zodiac" element={<Zodiac user={user}/>} />
				<Route path="/concord" element={<Concord user={user} />} />
				<Route path="/numerology" element={<Numerology user={user}/>} />
				<Route path="/about" element={<About user={user}/>} />

				<Route path="/luckyme" element={user ?
				<LuckyMe user={user}/>: navigateFromProtectedRoute} />

				<Route path="/edit/:userId" element={user ?
				<Edit user={user}/>: navigateFromProtectedRoute} />				
				<Route path ="/login" element={!user ? <LoginSignUp setUser={setUser} /> : navigateFromProtectedRoute} />
				<Route path ="/signup" element={!user ? <LoginSignUp setUser={setUser} /> : navigateFromProtectedRoute} />
				<Route path="*" element={<div>Page not found, 404</div>} />
			</Routes>
		</main>
		<footer>
					<Footer setUser={setUser} user={user}/>
		</footer>
	</div>

    </BrowserRouter>
  )
}

export default App
