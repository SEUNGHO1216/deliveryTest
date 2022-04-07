package com.sparta.delivery.dto;

import com.sparta.delivery.model.FoodOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {

    private String restaurantName;
    private List<FoodOrder> foods=new ArrayList<>();
    private int deliveryFee;
    private int plusDeliveryFee;
    private int totalDeliveryFee;
    private int totalPrice;


}
