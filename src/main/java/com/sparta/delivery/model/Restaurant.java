package com.sparta.delivery.model;

import com.sparta.delivery.dto.RestaurantRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Data
@Entity
public class Restaurant {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;

    @Column(nullable = false)
    private int x;
    @Column(nullable = false)
    private int y;

    public Restaurant(RestaurantRequestDto restaurantRequestDto){
        this.name=restaurantRequestDto.getName();
        this.minOrderPrice= restaurantRequestDto.getMinOrderPrice();
        this.deliveryFee= restaurantRequestDto.getDeliveryFee();
        this.x=restaurantRequestDto.getX();
        this.y=restaurantRequestDto.getY();
    }

}
