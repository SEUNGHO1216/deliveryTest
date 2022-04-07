package com.sparta.delivery.dto;


import com.sparta.delivery.model.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequestDto {

    private String username;
    private String password;
    private String nickname;

    private boolean admin = false;
    private String adminToken="";
    private String ownername;
}
