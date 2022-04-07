package com.sparta.delivery.model;

public enum UserRoleEnum {
    USER(Authority.USER), // 사용자 권한
    ADMIN(Authority.ADMIN); // 관리자 권한

    //왜 String 타입으로 authority를 받아주는지는 의문..
    private final String authority;

    UserRoleEnum(String authority){
        this.authority=authority;
    }
    public String getAuthority(){
        return this.authority;
    }

    //@Secured안에 쓸려면 static한 형태로 와줘야한다! 그래서 아래와 같이 만들어줌.
    public static class Authority{
        public static final String USER="ROLE_USER";
        public static final String ADMIN="ROLE_ADMIN";
    }
}