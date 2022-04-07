package com.sparta.delivery.model;

import com.sparta.delivery.dto.FoodOrderDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class FoodOrder {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private int price;

    public FoodOrder(FoodOrderDto foodOrderDto){
        this.name=foodOrderDto.getName();
        this.quantity=foodOrderDto.getQuantity();
        this.price=foodOrderDto.getPrice();
    }
}
