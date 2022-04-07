package com.sparta.delivery.model;

import com.sparta.delivery.dto.FoodOrderRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class FoodOrderRequest {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false)
    private Long foodId;
    @Column(nullable = false)
    private int quantity;

    public FoodOrderRequest(FoodOrderRequestDto foodOrderRequestDto){
        this.foodId=foodOrderRequestDto.getId();
        this.quantity=foodOrderRequestDto.getQuantity();
    }

}
