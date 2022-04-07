package com.sparta.delivery.controller;

import com.sparta.delivery.dto.SignupRequestDto;
import com.sparta.delivery.model.User;
import com.sparta.delivery.security.UserDetailsImpl;
import com.sparta.delivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @ResponseBody
    @PostMapping("/user/signup")
    public User signup(@RequestBody SignupRequestDto signupRequestDto){
        User user=userService.signup(signupRequestDto);
        return user;
    }

}
