package com.sparta.delivery.model;

import com.sparta.delivery.dto.FoodRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class Food {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private Long restaurantId;

    public Food(Long restaurantId, FoodRequestDto requestDto){
        this.restaurantId=restaurantId;
        this.name=requestDto.getName();
        this.price=requestDto.getPrice();
    }
}
