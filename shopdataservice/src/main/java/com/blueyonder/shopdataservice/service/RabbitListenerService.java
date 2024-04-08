package com.blueyonder.shopdataservice.service;

import com.blueyonder.shopdataservice.Exceptions.ProductNotFoundException;
import com.blueyonder.shopdataservice.Exceptions.TooManyCharactersException;
import com.blueyonder.shopdataservice.entities.MQObject;
import com.blueyonder.shopdataservice.entities.Product;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitListenerService {

    @Autowired
    ProductService pService;

    @RabbitListener(queues = {"q.product-log"})
    public void onUserRegistration(String message) throws ProductNotFoundException, TooManyCharactersException {
        System.out.print("Recieved message: ");
        System.out.println(message);
        //split message by comma
        String[] messageArray = message.split(",");

        Product prod = pService.getProductById(Integer.parseInt(messageArray[0]));
        int stockQuantity = Integer.parseInt(messageArray[1]);
        prod.setStockQuantity(prod.getStockQuantity() - stockQuantity);
        pService.updateProduct(prod);
    }
}

