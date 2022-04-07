package com.sparta.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RestaurantRequestDto {

    private String name;
    private int minOrderPrice;
    private int deliveryFee;
    private int x;
    private int y;
}
