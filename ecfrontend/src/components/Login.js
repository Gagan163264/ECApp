import React, { useEffect, useState, useRef } from 'react';
import { Link, useNavigate } from 'react-router-dom';

import * as THREE from 'three';
import NET from 'vanta/dist/vanta.net.min.js';
import axios from 'axios';



function Login({ setAuth, setUser }) {
    const [vantaEffect, setVantaEffect] = useState(null);
    const backgroundRef = useRef(null);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    let navigate = useNavigate();

    useEffect(() => {
      if (!vantaEffect) {
        setVantaEffect(
          NET({
            el: backgroundRef.current,
            THREE:THREE,
            mouseControls: true,
              touchControls: true,
              gyroControls: false,
              minHeight: 200.00,
              minWidth: 200.00,
              size: 0.50
            // showDots: false
          })
        );
      }
      return () => {
        if (vantaEffect) vantaEffect.destroy();
      };
    }, [vantaEffect]);

    const handleClick = async (e) => {
        e.preventDefault();
        const loginObj = {
            username: username,
            password: password
        };
        try {
            const response = await axios.post('http://localhost:8060/auth/login', loginObj);
            if (response.data && response.data === `Invalid Access`) {
              setError(response.data);
            } else {
              const token = response.data['token'];
              console.log(token);
              localStorage.setItem('token', token);
              localStorage.setItem('username', loginObj.username);
              setAuth(true);
              setUser(loginObj.username);
              //Redirect or handle successful login here
              navigate('/');
            }
          } catch (error) {
            console.error('Login error:', error);
            setError('Invalid Credentials! Please try again.');
          }
    }
    
return (
    <div
    ref={backgroundRef}
    className="vh-100 text-white"
    style={{ height: '100vh', width: '100%', position: 'fixed', top: 0, left: 0, zIndex: -1 }}
    >
            <section className="vh-100">
            <div className="container py-5 h-100">
            <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-12 col-md-8 col-lg-6 col-xl-5">
            <div className="card shadow-2-strong" style={{borderRadius: '1rem'}}>
            <div className="card-body p-5 text-center">

            <h3 className="mb-5">Sign in</h3>

            <div className="form-outline mb-4">
            <input type="Username" id="typeEmailX-2" className="form-control form-control-lg input-lg" placeholder = 'Enter Username' onChange={(e) => setUsername(e.target.value)}/>
            </div>

            <div className="form-outline mb-4">
            <input type="password" id="typePasswordX-2" className="form-control form-control-lg" placeholder = 'Enter Password' onChange={(e) => setPassword(e.target.value)}/>
            </div>
            <div className="form-check d-flex justify-content-start">
            <input className="form-check-input" type="checkbox" value="" id="form1Example3" />
            <label className="form-check-label mx-2 mb-3" htmlFor="form1Example3">Remember password </label>
            </div>
            {error!==''&&<div class="alert alert-danger mb-0" role="alert">
                <label className="form-check-label" htmlFor="form1Example3">{error}</label>
            </div>}
            </div>
            <div className="d-grid mx-5 mb-4 mt-0">
                <button className="btn btn-primary btn-lg btn-block" onClick={handleClick}>Login</button>

                <hr className="my-3" />

                <button className="btn btn-lg btn-block btn-primary" style={{backgroundColor: '#dd4b39'}}
                ><Link className='nav-link' to="/signup">Sign Up</Link></button>
            </div>
            </div>
            </div>
            </div>
            </div>
            </section>
    </div>
);
}

export default Login;