package com.cskaoyan.project1.model.vo.backstage;

import com.cskaoyan.project1.model.Goods;

public class Message_goodsVO {
    private String name;

    public Message_goodsVO(Goods goods) {
        this.name = goods.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
