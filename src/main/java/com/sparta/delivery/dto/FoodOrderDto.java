package com.sparta.delivery.dto;

import com.sparta.delivery.repository.FoodRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodOrderDto {


    private String name;
    private int price;
    private int quantity;

}
