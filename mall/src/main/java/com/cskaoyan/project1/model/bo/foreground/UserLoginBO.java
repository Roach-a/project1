package com.cskaoyan.project1.model.bo.foreground;

import com.cskaoyan.project1.model.User;

/**
 * 用户登录BO
 */
public class UserLoginBO {
    private String email;
    private String pwd;

    public UserLoginBO() {
    }
    public User toUser() {
        User user = new User();
        user.setEmail(email);
        user.setPwd(pwd);
        return user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
