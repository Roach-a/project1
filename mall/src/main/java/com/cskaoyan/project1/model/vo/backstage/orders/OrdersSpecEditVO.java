package com.cskaoyan.project1.model.vo.backstage.orders;

import com.cskaoyan.project1.model.Spec;

public class OrdersSpecEditVO {
    private Integer id;
    private String specName;
    private Double unitPrice;

    public OrdersSpecEditVO(Spec spec) {
        this.id = spec.getId();
        this.specName = spec.getSpecName();
        this.unitPrice = spec.getUnitPrice();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
