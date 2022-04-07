package com.sparta.delivery.controller;

import com.sparta.delivery.dto.RestaurantRequestDto;
import com.sparta.delivery.model.Restaurant;
import com.sparta.delivery.model.UserRoleEnum;
import com.sparta.delivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService){
        this.restaurantService=restaurantService;
    }

    //음식점 등록(사장님만 가능)
    @Secured(UserRoleEnum.Authority.ADMIN)
    @ResponseBody
    @PostMapping("/restaurant/register")
    public Restaurant saveRestaurant(@RequestBody RestaurantRequestDto requestDto){

        Restaurant restaurant=restaurantService.saveRestaurant(requestDto);

        return restaurant;
    }


    @Secured(UserRoleEnum.Authority.USER)
    @ResponseBody
    @GetMapping("/restaurants")
    public List<Restaurant> showAllRestaurants(){
        List<Restaurant> restaurantList=restaurantService.showRestaurants();
        return restaurantList;
    }
    //최대 3km 내에 위치하여 배달받을 수 있는 음식점 목록만 조회
    @Secured(UserRoleEnum.Authority.USER)
    @ResponseBody
    @GetMapping("/restaurants/delivery")
    public List<Restaurant> showAllDeliveryRestaurants(@RequestParam int x,@RequestParam int y){
        List<Restaurant> restaurantList=restaurantService.showDeliveryRestaurants(x, y);
        return restaurantList;
    }
}
