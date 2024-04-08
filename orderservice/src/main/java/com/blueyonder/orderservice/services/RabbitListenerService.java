package com.blueyonder.orderservice.services;


import com.blueyonder.orderservice.entities.ProductObject;
import com.blueyonder.orderservice.exceptions.TooManyItemsException;
import com.blueyonder.orderservice.repository.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitListenerService {

    @Autowired
    ProductRepository itemService;

    @RabbitListener(queues = {"q.product-log"})
    public void onUserRegistration(String message) throws TooManyItemsException {
        System.out.print("Recieved message: ");
        System.out.println(message);
        //split message by comma
        String[] messageArray = message.split(",");

        ProductObject prod = itemService.getItemByProductId(Integer.parseInt(messageArray[0]));
        int stockQuantity = Integer.parseInt(messageArray[1]);
        
        if (prod.getQuantity()> stockQuantity) {
            prod.setQuantity(stockQuantity);
            throw new TooManyItemsException("Not enough stock for product with id: " + messageArray[0]);
        }
    }
}

