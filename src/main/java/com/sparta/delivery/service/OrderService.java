package com.sparta.delivery.service;

import com.sparta.delivery.dto.FoodOrderDto;
import com.sparta.delivery.dto.FoodOrderRequestDto;
import com.sparta.delivery.dto.OrderDto;
import com.sparta.delivery.dto.OrderRequestDto;
import com.sparta.delivery.model.*;
import com.sparta.delivery.repository.FoodOrderRepository;
import com.sparta.delivery.repository.FoodRepository;
import com.sparta.delivery.repository.OrderRepository;
import com.sparta.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final FoodOrderRepository foodOrderRepository;

//    @Transactional
    public OrderDto saveOrder(OrderRequestDto orderRequestDto) {
        Restaurant restaurant=restaurantRepository.findById(orderRequestDto.getRestaurantId()).orElseThrow(
                ()->new IllegalArgumentException("해당 아이디가 없습니다")
        );

        String restaurantName= restaurant.getName();

        List<FoodOrder> foods=new ArrayList<>();

        //음식의 총액을 위한 빌드업
        int totalPrice=0;

        for(FoodOrderRequestDto foodOrderRequestDto: orderRequestDto.getFoods()){
            Food food=foodRepository.findById(foodOrderRequestDto.getId()).orElseThrow(
                    ()->new IllegalArgumentException("해당 아이디가 없습니다")
            );

            //조건 검사
            String name=food.getName();
            int quantity=foodOrderRequestDto.getQuantity();
            if(quantity<1){
                throw new IllegalArgumentException("음식수량을 선택하세요");
            }
            if(quantity>100){
                throw new IllegalArgumentException("음식수량은 100개 이하로 선택가능합니다.");
            }
            //음식 한 종류의 가격=수량 x 음식하나 가격
            int price=food.getPrice()*quantity;
            //음식 총 주문금액
            totalPrice+=price;
            if(totalPrice<restaurant.getMinOrderPrice()){
                throw new IllegalArgumentException("음식점 최소주문 가격 이상 주문 하셔야합니다");
            }
            FoodOrderDto foodOrderDto= new FoodOrderDto(name, price, quantity);

            FoodOrder foodOrder=new FoodOrder(foodOrderDto);
            foodOrderRepository.save(foodOrder);
            foods.add(foodOrder);
        }
        //기본배달비
        int deliveryFee=restaurant.getDeliveryFee();

        //1km당 500원 배달비 추가되는 거리 계산식
        int distanceSum=(int)Math.pow(restaurant.getX()-orderRequestDto.getX(),2)+(int)Math.pow(restaurant.getY()-orderRequestDto.getY(),2);
        int distance=(int)Math.sqrt(distanceSum);
        //추가 배달비
        int plusDeliveryFee=0;
        if(distance<=3.0){
            plusDeliveryFee=distance*500;
        }
        //총 배달비
        int totalDeliveryFee=deliveryFee+plusDeliveryFee;

        //총 배달비 포함 총 주문 금액
        totalPrice+=totalDeliveryFee;

        OrderDto orderDto =new OrderDto(restaurantName, foods, deliveryFee, plusDeliveryFee, totalDeliveryFee, totalPrice);
        Orders orders = new Orders(orderDto);
        orderRepository.save(orders);

        return orderDto;
    }
    //주문 목록 출력
    public List<Orders> showOrder() {
        List<Orders> orderList=orderRepository.findAll();
        List<Orders> order=new ArrayList<>();
        for(int i = orderList.size()-1 ; i<orderList.size();i++){
            order.add(orderList.get(i));
        }
        return order;
    }

}
