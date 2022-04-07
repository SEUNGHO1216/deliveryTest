package com.sparta.delivery.security;

import com.sparta.delivery.model.User;
import com.sparta.delivery.model.UserRoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user){
        this.user=user;
    }
    public User getUser(){
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UserRoleEnum role=user.getRole();
        String authority=role.getAuthority();

        SimpleGrantedAuthority simpleGrantedAuthority= new SimpleGrantedAuthority(authority);//"ROLE_" 규칙을 지켜야한다! 그래서 롤네임을 authority로 갖고온것!
        //근데 애초에 userRoleEnum에서 "Role_"로 줬으면..?
        Collection<GrantedAuthority> authorities= new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        //결국에 이값을 넘겨줌으로써 스프링 시큐리티가 이 유저가 user인지 admin인지 알수 있게끔 함.
        //그에 따라 @Secured도 쓸수 있고 권한 먹일 수있음음
       return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
