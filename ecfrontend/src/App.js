import React, { useEffect, useState } from 'react';
import {BrowserRouter,Route,Routes} from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';
import "bootstrap-icons/font/bootstrap-icons.css";


import Home from './components/Home';
import Navbar from './components/Navbar';
import Login from './components/Login';
import Logout from './components/Logout';
import SignUp from './components/Register';
import ProductsPage from './components/ProductsPage';
import CartView from './components/CartView';
import AddProduct from './components/AddProduct';

function App() {
  const [authenticated, setAuthenticated] = useState(false);
  const [username, setUsername] = useState('USER');
  const [navstate, setNavstate] = useState(false);
  const [products, setProducts] = useState([]);

  useEffect(() => {
    let item1 = {id:1, name: "Mouse G102", description: "Computer Mouse, 1200DPI", price: 1200};
    let item2 = {id:2, name: "Keyboard K120", description: "Computer Keyboard, Wired", price: 1500};
    let item3 = {id:3, name: "Monitor 24", description: "Computer Monitor, 24 inch", price: 12000};
    let item4 = {id:4, name: "Monitor 27", description: "Computer Monitor, 27 inch", price: 15000};
    let item5 = {id:5, name: "Monitor 32", description: "Computer Monitor, 32 inch", price: 20000};
    setProducts([item1, item2, item3, item4, item5]);

    setNavstate(false);
    const token = localStorage.getItem('token');
    console.log(token)
    if (token) {
      setAuthenticated(true);
      setUsername(localStorage.getItem('username'));
    }
  }, []);
  return (
    <BrowserRouter>
        <Navbar auth = {authenticated} username={username} navState={navstate}/>
        <Routes>
          <Route path="/" element={<Home/>}/>
          {!authenticated && <Route path="/login" element={<Login setAuth={setAuthenticated} setUser={setUsername}/>}/>}
          {authenticated && <Route path="/logout" element={<Logout/>} />}
          {<Route path = "signup" element = {<SignUp/>} />}
          {authenticated && <Route path="/products" element={<ProductsPage setNavstate={setNavstate} products={products}/>}/>}
          {authenticated && <Route path="/addproduct" element={<AddProduct setNavstate={setNavstate} setProducts={setProducts}/>}/>}
          {authenticated && <Route path="/deleteproduct" element={<ProductsPage setNavstate={setNavstate} setProducts={setProducts}/>}/>}
          {authenticated && <Route path="/cart" element={<CartView/>}/>}
        </Routes>
    </BrowserRouter>
  );
}

export default App;