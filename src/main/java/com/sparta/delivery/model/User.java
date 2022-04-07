package com.sparta.delivery.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Table(name = "Users")
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = true)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;
    @Column(nullable = true)
    private String ownername;

    public User(String username, String password, String nickname,UserRoleEnum role,String ownername){
        this.username=username;
        this.password=password;
        this.nickname=nickname;
        this.role=role;
        this.ownername=ownername;
    }
}
