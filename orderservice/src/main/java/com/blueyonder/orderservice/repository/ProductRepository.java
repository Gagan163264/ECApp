package com.blueyonder.orderservice.repository;

import com.blueyonder.orderservice.entities.CartItems;
import com.blueyonder.orderservice.entities.ProductObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductObject, Integer> {
    ProductObject getItemByProductId(int i);
}
