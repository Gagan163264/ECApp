import React, { useEffect, useState, useRef} from 'react';

import * as THREE from 'three';
import axios from 'axios';
import RINGS from 'vanta/dist/vanta.rings.min.js';

import ProductCard from './ProductCard';

function ProductsPage({setNavstate, products}) {
    const [vantaEffect, setVantaEffect] = useState(null);
    const backgroundRef = useRef(null);
    useEffect(() => {
        const getResponse = async () => {
            const token = localStorage.getItem('token');
            const response = axios.get('http://localhost:8060/shopdataservice/ecapp/v1/product/getallproducts', {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            console.log(response); 
            if(response.data){
                setProducts(response.data);
            }
           setNavstate(true);
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
        {products.map((product) => <ProductCard product={product} />)}
        </div>
        </div>
        </div>

        
    </div>
  );
}

export default ProductsPage;