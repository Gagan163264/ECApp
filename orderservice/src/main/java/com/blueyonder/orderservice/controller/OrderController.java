package com.blueyonder.orderservice.controller;

import com.blueyonder.orderservice.entities.CartItems;
import com.blueyonder.orderservice.entities.MQObject;
import com.blueyonder.orderservice.exceptions.InvalidChangeInCart;
import com.blueyonder.orderservice.exceptions.InvalidTokenType;
import com.blueyonder.orderservice.exceptions.UserNotFoundException;
import com.blueyonder.orderservice.services.CartService;

import java.util.HashMap;

import com.blueyonder.orderservice.services.JjwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecapp/v1/order")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    CartService cartService;

    private final RabbitTemplate rabbitTemplate;
    public OrderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/getcart")
    public ResponseEntity<Object> getCart(@RequestHeader("Authorization") String authorizationHeader) throws UserNotFoundException, InvalidTokenType {
        String userName;
        try{
            String token = authorizationHeader.substring(7);
            userName = JjwtUtils.extractUsername(token);
        }catch (Exception e)
        {
            throw new InvalidTokenType("Invalid Token");
        }
        CartItems cartItems = cartService.getCartbyUserName(userName);
        HashMap<String, Object> response = new HashMap<>();
        response.put("Items", cartItems);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/editproduct")
    public ResponseEntity<Object> addProductToCart(@RequestHeader("Authorization") String authorizationHeader, @RequestParam Integer productId, @RequestParam Integer quantity) throws JsonProcessingException, InvalidChangeInCart, InvalidTokenType {
        String userName;
        try{
            String token = authorizationHeader.substring(7);
            userName = JjwtUtils.extractUsername(token);
        }catch (Exception e)
        {
            throw new InvalidTokenType("Invalid Token");
        }
        CartItems cartItems = cartService.editProductInCart(userName, productId, quantity);
        MQObject mqo = new MQObject(productId,quantity);
        HashMap<String, Object> response = new HashMap<>();
        response.put("Items", mqo);

        String message = productId.toString()+","+quantity.toString();

        rabbitTemplate.convertAndSend("", "q.product-log", message);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/removeproduct")
    public ResponseEntity<Object> removeProductFromCart(@RequestHeader("Authorization") String authorizationHeader, @RequestParam Integer productId) throws UserNotFoundException, InvalidTokenType {
        String userName;
        try{
            String token = authorizationHeader.substring(7);
            userName = JjwtUtils.extractUsername(token);
        }catch (Exception e)
        {
            throw new InvalidTokenType("Invalid Token");
        }
        CartItems cartItems = cartService.removeProductFromCart(userName, productId);
        HashMap<String, Object> response = new HashMap<>();
        response.put("Items", cartItems);
        return ResponseEntity.ok(response);
    }
}
