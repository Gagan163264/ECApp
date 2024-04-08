package com.blueyonder.orderservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;

    private Integer productId;
    private Integer quantity;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "cartId")
    @ToString.Exclude
    private CartItems items;

    public ProductObject(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
