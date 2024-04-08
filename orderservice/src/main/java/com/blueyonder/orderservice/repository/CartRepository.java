package com.blueyonder.orderservice.repository;

import com.blueyonder.orderservice.entities.CartItems;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<CartItems, Integer> {
    CartItems findByuserName(String username);
}
