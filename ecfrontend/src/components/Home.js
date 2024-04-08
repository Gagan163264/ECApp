import React, { useEffect, useState, useRef } from 'react';
import * as THREE from 'three';
import GLOBE from 'vanta/dist/vanta.globe.min.js';
import {ReactTyped} from "react-typed";


function Home(){
  let messages = ["Fast delivery. No excuses", "Omnipresent. Omnipotent. Omniscient", "We deliver. You recieve"];
  const [vantaEffect, setVantaEffect] = useState(null);
  const backgroundRef = useRef(null);
  useEffect(() => {
    if (!vantaEffect) {
      setVantaEffect(
        GLOBE({
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
  
    return(
      <>
        <div
        ref={backgroundRef}
        className="vh-100 text-white"
        style={{ height: '100vh', width: '100%', position: 'fixed', top: 0, left: 0, zIndex: -1 }}
        >
        
          <div className="h-100 d-flex align-items-center ml-5 pl-5 justify-content-left" style={{opacity:0.5}}>
        <div class="card">
        <div class="card-body" >
            <h1 class = ''><strong>
          <ReactTyped
              strings={messages}
              typeSpeed={100}
              loop
              backSpeed={20}
              cursorChar=">"
              showCursor={true}
          />
          </strong></h1>
          </div>
        </div>
        </div>

        </div>
       </>
    )
}
export default Home;