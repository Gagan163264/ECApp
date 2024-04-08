package com.blueyonder.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "Cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;
    private String userName;
    @OneToMany(mappedBy = "items", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("items")
    @ToString.Exclude
    private Set<ProductObject> items;

}
