import React from 'react';
import { Link, Outlet } from 'react-router-dom';
function Navbar({auth, username, navState}){
    return(
        <>
        <nav className="navbar navbar-expand-lg navbar-light  justify-content-between" style={{backgroundColor:" #e3f2fd", opacity: "0.9"}}>
            <a className="navbar-brand" href="/">EcommerceApp</a>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
                <ul className="navbar-nav">
                {<li className="nav-item">
                <nav class="navbar navbar-light">
                    <form class="form-inline">
                        <div class="input-group">
                            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"></input>
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                        </div>
                    </form>
                    </nav>
                </li>}
                </ul>
                <ul className="navbar-nav">
                {auth &&<li>
                    <Link className='nav-link' to="/products">Products</Link>
                </li>}
                {navState&&auth &&<li>
                    <Link className='nav-link' to="/addproducts">Add Products</Link>
                    </li>}
                {navState&&auth &&<li>
                    <Link className='nav-link' to="/deleteproducts">Delete Products</Link>
                    </li>}
                {!navState&&auth &&<li>
                    <Link className='nav-link' to="/category">Category</Link>
                </li>}
                </ul>
                <div class="collapse navbar-collapse justify-content-end" id="navbarCollapse">
                <ul class="navbar-nav">
                {!auth && <li className="nav-item" >
                  <Link className='nav-link' to="/login">Login  <i class="bi bi-door-closed-fill"></i></Link>
                </li>}
                {auth && <li className="nav-item">
                  <Link className='nav-link' to="/cart">Cart  <i class="bi bi-cart4"></i></Link>
                </li>}
                {auth &&<li className='nav-item border-dark'>
                    <Link className='nav-link'>Hello, {username}</Link>
                    </li>}
                {auth &&<li>
                    <div class = "d-inline">
                    <Link className='nav-link' to="/logout">Logout  <i class="bi bi-door-open-fill"></i></Link>
                    </div>    
                </li>}
                </ul>
                </div>
            </div>
        </nav>
        <Outlet/>
        </>
    )
}
export default Navbar;