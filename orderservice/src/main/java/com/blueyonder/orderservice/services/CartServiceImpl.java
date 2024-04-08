package com.blueyonder.orderservice.services;

import com.blueyonder.orderservice.entities.CartItems;
import com.blueyonder.orderservice.entities.ProductObject;
import com.blueyonder.orderservice.exceptions.InvalidChangeInCart;
import com.blueyonder.orderservice.exceptions.UserNotFoundException;
import com.blueyonder.orderservice.repository.CartRepository;
import com.blueyonder.orderservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public CartItems getCartbyUserName(String userName) throws UserNotFoundException {
        CartItems cartItems = cartRepository.findByuserName(userName);
        if(cartItems == null){
            throw new UserNotFoundException("Cart not found for user : "+userName);
        }
        return cartItems;
    }

    public CartItems editProductInCart(String userName, Integer productId, Integer quantity) throws InvalidChangeInCart {
        if(quantity==0)
            throw new InvalidChangeInCart("Zero Change not allowed");
        CartItems cartItems = cartRepository.findByuserName(userName);
        ProductObject newProduct = new ProductObject(productId, quantity);

        if(cartItems == null){
            if(quantity<0)
                throw new InvalidChangeInCart("Number of products in cart less than zero");
            cartItems = new CartItems();
            Set<ProductObject> productIds = new HashSet<>();
            cartItems.setUserName(userName);
            newProduct.setItems(cartItems);
            productIds.add(newProduct);
            cartItems.setItems(productIds);
        }
        else{
            newProduct.setItems(cartItems);
            Set<ProductObject> oldProductIds = cartItems.getItems();
            Set<ProductObject> productIds = new HashSet<>();

            boolean flag = true;
            for(ProductObject product : oldProductIds){
                if(product.getProductId().equals(productId)){
                    int newQuantity =  product.getQuantity() + quantity;
                    if(newQuantity<0)
                        throw new InvalidChangeInCart("Number of products in cart less than zero");
                    product.setQuantity(newQuantity);
                    productIds.add(product);
                    flag = false;
                }
                else {
                    productIds.add(product);
                }
            }
            if(flag) {
                if(quantity<0)
                    throw new InvalidChangeInCart("Number of products in cart less than zero");
                productIds.add(newProduct);
            }
            cartItems.setItems(productIds);
        }

        cartRepository.save(cartItems);
        return cartItems;
    }

    public CartItems removeProductFromCart(String userName, Integer productId) throws UserNotFoundException {
        CartItems cartItems = cartRepository.findByuserName(userName);

        if(cartItems == null){
            throw new UserNotFoundException("Cart not found for user : "+userName);
        }

        cartRepository.deleteById(cartItems.getCartId());

        Set<ProductObject> productIds = cartItems.getItems();
        Set<ProductObject> newProductIds = new HashSet<>();
        for(ProductObject product : productIds){
            System.out.println(product.getProductId());
            System.out.println(productId);
            if(!product.getProductId().equals(productId)){
                newProductIds.add(product);
            }
        }
        cartItems.setItems(newProductIds);
        CartItems citems= cartRepository.save(cartItems);
        return citems;
    }
}
