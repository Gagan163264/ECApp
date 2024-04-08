import React, { useEffect, useState, useRef} from 'react';

import * as THREE from 'three';
import axios from 'axios';
import RINGS from 'vanta/dist/vanta.rings.min.js';

import CartItems from './CartItems';

function CartView({setNavstate}) {
    const [vantaEffect, setVantaEffect] = useState(null);
    const backgroundRef = useRef(null);
  const [products, setProducts] = useState([]);
    useEffect(() => {
        const getResponse = async () => {
            // const token = localStorage.getItem('token');
            // const response = axios.get('http://localhost:8091/shopdataservice/ecapp/v1/product/getallproducts', {
            //     headers: {
            //         Authorization: `Bearer ${token}`
            //     }
            // });
            // if(response.data){
            //     setProducts(response.data);
            // }
            let item1 = {id:1, name: "Mouse G102", description: "Computer Mouse, 1200DPI", price: 1200};
            let item2 = {id:2, name: "Keyboard K120", description: "Computer Keyboard, Wired", price: 1500};
            let item3 = {id:3, name: "Monitor 24", description: "Computer Monitor, 24 inch", price: 12000};
            let item4 = {id:4, name: "Monitor 27", description: "Computer Monitor, 27 inch", price: 15000};
            let item5 = {id:5, name: "Monitor 32", description: "Computer Monitor, 32 inch", price: 20000};
            setNavstate(true);
            setProducts([item1, item2, item3, item4, item5]);
        }
        getResponse();
        if (!vantaEffect) {
            setVantaEffect(
              RINGS({
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
  return (
    <div
    ref={backgroundRef}
    className="vh-100 text-white mt-5"
    style={{ height: '100vh', width: '100%', position: 'fixed', top: 0, left: 0, zIndex: -1 }}
    >
        <div className="container py-5 h-100">
        <div class = 'justify-content-center align-items-center'>
        <div class='row row-cols-1 row-cols-md-2 g-4'>
        {products.map((product) => <CartItems product={product} />)}
        </div>
        </div>
        </div>

        
    </div>
  );
}

export default CartView;