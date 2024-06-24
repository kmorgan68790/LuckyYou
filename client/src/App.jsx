import { useEffect, useState } from 'react';
import NavBar from './components/NavBar';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Home from './pages/Home'

function App() {
  const initialUser = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null
	const [user, setUser] = useState(initialUser)
	const navigateFromProtectedRoute = <Navigate to="/"/>;

  return (
    <BrowserRouter>
    <div className='container'>
				<header className='mb-3'>
					<nav className='navbar navbar-expand'>
						<div className='d-flex'>
							{/* <a className='navbar-brand' href='/'>
								<img src={logo} alt='Solar Farm' width='150' />
							</a> */}
							<NavBar setUser={setUser} user={user}/>
						</div>
					</nav>
				</header>
				<main>
					<h1 className='mb-3'>Lucky You</h1>
					{user !== null ? <h3>Welcome, {user.username}</h3> : null}
					<Routes>
						<Route path="/" element={<Home />} />
						{/* <Route path="/contact" element={<Contact />} /> */}
						{/* <Route path="/allPanels" element={<AllPanels user={user}/>} /> */}

						<Route path="/personal" element={user ?
                            <MyPanels user={user}/>: navigateFromProtectedRoute} />
						<Route path="/add"  element={user ?
                            <Add user={user}/>: navigateFromProtectedRoute} />
						<Route path="/edit/:id" element={user ?
                            <Edit user={user}/>: navigateFromProtectedRoute} />				
						<Route path="/delete/:id" element={user ?
                            <ConfirmDelete user={user}/>: navigateFromProtectedRoute} />

						{/* <Route path ="/login" element={!user ? <LoginSignUp setUser={setUser} /> : navigateFromProtectedRoute} />
						<Route path ="/signup" element={!user ? <LoginSignUp setUser={setUser} /> : navigateFromProtectedRoute} />
						<Route path="*" element={<div>Page not found, 404</div>} /> */}
					</Routes>
				</main>
			</div>
 
    </BrowserRouter>
  )
}

export default App
