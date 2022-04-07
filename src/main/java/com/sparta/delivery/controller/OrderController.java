package com.sparta.delivery.controller;

import com.sparta.delivery.dto.OrderDto;
import com.sparta.delivery.dto.OrderRequestDto;
import com.sparta.delivery.model.Orders;
import com.sparta.delivery.model.UserRoleEnum;
import com.sparta.delivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;

    //주문하기
    @Secured(UserRoleEnum.Authority.USER)
    @ResponseBody
    @PostMapping("/order/request")
    public OrderDto saveOrder(@RequestBody OrderRequestDto orderRequestDto){

        OrderDto orderDto=orderService.saveOrder(orderRequestDto);
        return orderDto;
    }

    //주문보기
    @Secured(UserRoleEnum.Authority.USER)
    @ResponseBody
    @GetMapping("/orders")
    public List<Orders> showOrders(){
        List<Orders> ordersList=orderService.showOrder();
        return ordersList;
    }

}
