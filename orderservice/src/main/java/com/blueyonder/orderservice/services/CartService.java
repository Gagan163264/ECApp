package com.blueyonder.orderservice.services;

import com.blueyonder.orderservice.entities.CartItems;
import com.blueyonder.orderservice.exceptions.InvalidChangeInCart;
import com.blueyonder.orderservice.exceptions.UserNotFoundException;

public interface CartService {
    public CartItems getCartbyUserName(String userName) throws UserNotFoundException;
    CartItems editProductInCart(String userName, Integer productId, Integer quantity) throws InvalidChangeInCart;

    CartItems removeProductFromCart(String userName, Integer productId) throws UserNotFoundException;

    CartItems getCartItemByProductId(int i);
}
