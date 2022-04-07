package com.sparta.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequestDto {

    private Long restaurantId;
    private List<FoodOrderRequestDto> foods =new ArrayList<>();
    private int x;
    private int y;


}
