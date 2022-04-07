package com.sparta.delivery.service;

import com.sparta.delivery.dto.RestaurantRequestDto;
import com.sparta.delivery.model.Restaurant;
import com.sparta.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant saveRestaurant(RestaurantRequestDto requestDto){
        if(requestDto.getMinOrderPrice()<1000){
            throw new IllegalArgumentException("천원 이상 주문해야 합니다.");
        }
        if(requestDto.getMinOrderPrice()>100_000){
            throw new IllegalArgumentException("십만원 이하로 주문해야 합니다.");
        }
        if(requestDto.getMinOrderPrice()%100 != 0){
            throw new IllegalArgumentException("주문금액은 100원 단위까지 입력 가능합니다.");
        }
        if(requestDto.getDeliveryFee()<0){
            throw new IllegalArgumentException("0원 미만으로 배달비를 입력할 수 없습니다.");
        }
        if(requestDto.getDeliveryFee()>10_000){
            throw new IllegalArgumentException("배달비는 만원 이하로 입력해야 합니다.");
        }
        //배달비는 500원 단위로만 설정 가능
        if(requestDto.getDeliveryFee()%500 !=0){
            throw new IllegalArgumentException("배달비는 500원 단위로 입력 가능합니다.");
        }
        //음식점 위치 설정
        if(requestDto.getX()<0 || requestDto.getX()>99
        || requestDto.getY()<0 || requestDto.getY()>99){
            throw new IllegalArgumentException("위치입력은 0~99 의 숫자만 가능합니다");
        }
        Restaurant restaurant= new Restaurant(requestDto);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    //모든 음식점 리스트 반환
    public List<Restaurant> showRestaurants(){
        List<Restaurant> restaurantList=restaurantRepository.findAll();
        return restaurantList;
    }

    //배달 가능한 음식점 리스트 반환
    public List<Restaurant> showDeliveryRestaurants(double x, double y) {
        List<Restaurant> restaurantList=restaurantRepository.findAll();
        List<Restaurant> deliveryPossibleRestaurant=new ArrayList<>();
        for(Restaurant restaurant: restaurantList){
            double distance=Math.abs(restaurant.getX()-x)+Math.abs(restaurant.getY()-y);
            if(distance<=3.0){
                //거리 3km 이내인 것들 중, 1km당 500원 배달비 할증!
                int distanceSum=(int)Math.pow(restaurant.getX()-x,2)+(int)Math.pow(restaurant.getY()-y,2);
                int dist=(int)Math.sqrt(distanceSum);
                int plusDeliveryFee=dist*500;
                restaurant.setDeliveryFee(restaurant.getDeliveryFee()+plusDeliveryFee);
                deliveryPossibleRestaurant.add(restaurant);
            }
        }
        return deliveryPossibleRestaurant;
    }
}
