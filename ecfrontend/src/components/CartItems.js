import React, { useEffect, useState } from 'react';
import axios from 'axios';


function CartItems({ product }) {
    const [ProductImage, setProductImage] = useState(null);
    const [productPrice, setProductPrice] = useState(null);

    const addItem = () => {
        console.log("Add Item");
    }

    const removeItem = () => {
        console.log("Remove Item");
    }

    const deleteItem = () => {
        console.log("Delete Item");
    }   
    useEffect(() => {
        console.log(product.name);
        const options = {
        method: 'GET',
        url: 'https://keyword-to-image.p.rapidapi.com/',
        params: {
            keyword: product.name,
            index: '2'
        },
        headers: {
            'X-RapidAPI-Key': '5414f9d3e0mshb887539461fdda2p1544a8jsndb0291c8d570',
            'X-RapidAPI-Host': 'keyword-to-image.p.rapidapi.com'
        }
        };

        try {
        const response = axios.request(options);
        setProductImage(response.data);
        } catch (error) {
        console.error(error);
        }

    }, []);
    return (
        <div className="col-md-2">
            <div className="card">
                {/* <img src={ProductImage} className="card-img-top" alt="..." /> */}
                <div className="card-body">
                    <h5 className="card-title">{product.name}</h5>
                    <p className="card-text">{product.quantity}</p>
                    <p className="card-text">Price: {product.price}</p>
                    <div className="d-flex justify-content-right">
                        <button className="btn btn-primary px-auto" onClick={addItem}>Add</button>
                        <button className="btn btn-primary px-auto" onClick={removeItem}>Remove</button>
                        <button className="btn btn-primary px-auto" onClick={deleteItem}>Delete</button>
                    </div>    
                </div>
            </div>
        </div>
    );
}

export default CartItems;