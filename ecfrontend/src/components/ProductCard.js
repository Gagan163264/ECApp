import React, { useEffect, useState } from 'react';
import axios from 'axios';


function ProductCard({ product }) {
    const [ProductImage, setProductImage] = useState(null);
    useEffect(() => {
        const options = {
        method: 'GET',
        url: 'https://api.unsplash.com/search/photos?page=1&query=office',
        params: {
            page: '1',
            query: product.name
        }
        };

        try {
        const response = axios.request(options);
        setProductImage(response.data);
        } catch (error) {
        console.error(error);
        }

    }, []);

    const addItem = () => {
        console.log("Add Item");
    }
    return (
        <div className="col-md-2">
            <div className="card">
                {/* <img src={ProductImage} className="card-img-top" alt="..." /> */}
                <div className="card-body">
                    <h5 className="card-title">{product.name}</h5>
                    <p className="card-text">{product.description}</p>
                    <p className="card-text">Price: {product.price}</p>
                    <div className="d-flex justify-content-right">
                        <button className="btn btn-primary" onClick={addItem}>Add to Cart</button>
\                    </div>      
                </div>
            </div>
        </div>
    );
}

export default ProductCard;