package com.sparta.delivery.service;

import com.sparta.delivery.dto.SignupRequestDto;
import com.sparta.delivery.model.User;
import com.sparta.delivery.model.UserRoleEnum;
import com.sparta.delivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private static final String ADMIN_TOKEN="seung1216seungho1216";

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository= userRepository;
        this.passwordEncoder=passwordEncoder;
    }



    public User signup(SignupRequestDto signupRequestDto){
        String username=signupRequestDto.getUsername();
        String password=signupRequestDto.getPassword();
        //패스워드 암호화
        String encodedPassword=passwordEncoder.encode(password);

        Optional<User> userOptional=userRepository.findByUsername(username);
        if(userOptional.isPresent()){
            throw new IllegalArgumentException("중복된 아이디 입니다.");
        }
        UserRoleEnum role=UserRoleEnum.USER;
        String ownername=null;
        if(signupRequestDto.isAdmin()){
            if(!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)){
                throw new IllegalArgumentException("관리자 토큰이 일치하지 않습니다");
            }
            role=UserRoleEnum.ADMIN;
            ownername=signupRequestDto.getOwnername();
        }
        String nickname=signupRequestDto.getNickname();
        User user=new User(username,encodedPassword, nickname,role, ownername);
        userRepository.save(user);
        return user;
    }

    public User getLogoutUser(Long id) {
        User user=userRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("아이디없음")
        );
        return user;
    }

    public List<User> getLoginUser() {
        List<User> userList=userRepository.findAll();
        return userList;
    }
}
