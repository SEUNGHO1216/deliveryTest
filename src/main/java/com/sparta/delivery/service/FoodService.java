package com.sparta.delivery.service;

import com.sparta.delivery.dto.FoodRequestDto;
import com.sparta.delivery.model.Food;
import com.sparta.delivery.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Transactional(rollbackFor = Exception.class)
    public void saveFood(Long id, List<FoodRequestDto> foodRequestDtoList) {
        for(FoodRequestDto foodRequestDto:foodRequestDtoList) {
            if (foodRequestDto.getPrice() < 100) {
                throw new IllegalArgumentException("음식 가격은 100원 이상이어야 합니다.");
            }
            if (foodRequestDto.getPrice() > 1_000_000) {
                throw new IllegalArgumentException("음식 가격은 백만원 이하이어야 합니다.");
            }
            if (foodRequestDto.getPrice() % 100 != 0) {
                throw new IllegalArgumentException("음식 가격은 100원 단위까지 입력가능합니다.");
            }
            //음식 중복여부 판단
            List<Food> foods=foodRepository.findByRestaurantIdAndName(id, foodRequestDto.getName());
            if(foods.size()!=0){
                throw new IllegalArgumentException("증복된 음식입니다");
            }
            Food food =new Food(id, foodRequestDto);
            foodRepository.save(food);
        }
    }

    public List<Food> showFoods(Long restaurantId) {
        List<Food> foodList=foodRepository.findAllByRestaurantId(restaurantId);
        return foodList;
    }
}
