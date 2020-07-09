package com.cskaoyan.project1.model.vo.foreground;

import com.cskaoyan.project1.model.User;

//用户注册和用户登录都使用此VO
public class UserLoginAndRegisterVO {
    private Integer code;
    private String name;
    private String token;
    public UserLoginAndRegisterVO(){};
    public UserLoginAndRegisterVO(User user){
        code = 0;
        name = user.getNickname();
        token = user.getNickname();
    };

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserLoginAndRegisterVO{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
