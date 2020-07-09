package com.cskaoyan.project1.model.vo.backstage;

import com.cskaoyan.project1.model.User;

public class Message_userVO {
    private String name;

    public Message_userVO(User user) {
        this.name = user.getNickname();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
