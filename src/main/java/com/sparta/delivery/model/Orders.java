package com.sparta.delivery.model;

import com.sparta.delivery.dto.FoodOrderDto;
import com.sparta.delivery.dto.OrderDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Table(name = "orders")
@Data
@Entity
public class Orders {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String restaurantName;

    @JoinColumn(name="foodsList", nullable = false)
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<FoodOrder> foods = new ArrayList<>();

    @Column(nullable = false)
    private int deliveryFee;

    @Column(nullable = false)
    private int plusDeliveryFee;

    @Column(nullable = false)
    private int totalDeliveryFee;

    @Column(nullable = false)
    private int totalPrice;

    public Orders(OrderDto orderDto){
        this.restaurantName=orderDto.getRestaurantName();
        this.foods=orderDto.getFoods();
        this.deliveryFee=orderDto.getDeliveryFee();
        this.plusDeliveryFee=orderDto.getPlusDeliveryFee();
        this.totalDeliveryFee=orderDto.getTotalDeliveryFee();
        this.totalPrice=orderDto.getTotalPrice();
    }

}
