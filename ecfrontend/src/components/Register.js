import React, { useEffect, useState, useRef } from 'react';
import {Link} from 'react-router-dom';

import * as THREE from 'three';
import NET from 'vanta/dist/vanta.net.min.js';
import axios from 'axios';



function SignUp({ setAuth, setUser }) {
    const [vantaEffect, setVantaEffect] = useState(null);
    const backgroundRef = useRef(null);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [password2, setPassword2] = useState('');
    const [success, setSuccess] = useState('');
    const [email, setEmail] = useState('');
    const [error, setError] = useState('');

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
        if(password!==password2){
            setError('Passwords do not match');
            return;
        }
        const loginObj = {
            username: username,
            password: password,
            email: email
        };
        try {
            const response = await axios.post('http://localhost:8060/auth/register', loginObj);
            if (response.data && response.data === `Invalid Access`) {
              setError(response.data);
            } else {
              setSuccess("Signup completed successfully! Please login to continue.");
            }
          } catch (error) {
            console.error('Signup error:', error);
            setError('Something went wrong!');
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

            <h3 className="mb-5">Sign Up!!</h3>

            <div className="form-outline mb-4">
            <input type="Username" id="typeEmailX-2" className="form-control form-control-lg input-lg" placeholder = 'Enter Username' onChange={(e) => setUsername(e.target.value)}/>
            </div>

            <div className="form-outline mb-4">
            <input type="Email" id="typeEmailX-2" className="form-control form-control-lg input-lg" placeholder = 'Enter Email' onChange={(e) => setEmail(e.target.value)}/>
            </div>

            <div className="form-outline mb-4">
            <input type="password" id="typePasswordX-2" className="form-control form-control-lg" placeholder = 'Enter Password' onChange={(e) => setPassword(e.target.value)}/>
            </div>

            <div className="form-outline mb-4">
            <input type="password" id="typePasswordX-2" className="form-control form-control-lg" placeholder = 'Confirm Password' onChange={(e) => setPassword2(e.target.value)}/>
            </div>

            {error!==''&&<div class="alert alert-danger mb-0" role="alert">
                <label className="form-check-label" htmlFor="form1Example3">{error}</label>
            </div>}
            {success!==''&&<div class="alert alert-success mb-3" role="alert">
                <label className="form-check-label" htmlFor="form1Example3">{success}</label>
            </div>}
            <div className="d-grid mx-5 mb-4 mt-0">
                {success===''&&error === ''&&<button className="btn btn-primary btn-lg btn-block" onClick={handleClick}>Sign Up</button>}
                {success !== ''&&<button className="btn btn-primary btn-lg btn-block"><Link className='nav-link' to="/login">Return to login</Link></button>}
            </div>
            </div>
            </div>
            </div>
            </div>
            </div>
            </section>
    </div>
);
}

export default SignUp;