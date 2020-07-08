package com.cskaoyan.project1.model.vo;

import java.util.List;

public class PageOrdersVO {
    private Integer total;
    private List<PageOrdersInfoVO> orders;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<PageOrdersInfoVO> getOrders() {
        return orders;
    }

    public void setOrders(List<PageOrdersInfoVO> orders) {
        this.orders = orders;
    }
}
