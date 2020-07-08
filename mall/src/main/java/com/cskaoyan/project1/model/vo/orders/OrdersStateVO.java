package com.cskaoyan.project1.model.vo.orders;

import com.cskaoyan.project1.model.enumaration.OrderState;

public class OrdersStateVO {
    private Integer id;
    private String name;

    public OrdersStateVO() {
    }
    public OrdersStateVO(OrderState orderState) {
        this.id = orderState.getCode();
        this.name = orderState.getValue();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
