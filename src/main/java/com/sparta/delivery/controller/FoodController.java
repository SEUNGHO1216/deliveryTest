package com.sparta.delivery.controller;

import com.sparta.delivery.dto.FoodRequestDto;
import com.sparta.delivery.model.Food;
import com.sparta.delivery.model.UserRoleEnum;
import com.sparta.delivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class FoodController {

    private final FoodService foodService;
    //사장님만 가능
    //음식등록
    @Secured(UserRoleEnum.Authority.ADMIN)
    @ResponseBody
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void saveFood(@PathVariable Long restaurantId, @RequestBody List<FoodRequestDto> foodRequestDtoList){
        foodService.saveFood(restaurantId, foodRequestDtoList);
    }
    @Secured(UserRoleEnum.Authority.USER)
    @ResponseBody
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> showFoods(@PathVariable Long restaurantId){
        List<Food> foodList=foodService.showFoods(restaurantId);
        return foodList;
    }
}
