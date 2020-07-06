package com.cskaoyan.project1.model;

import com.cskaoyan.project1.model.bo.SpecBO;

public class Spec {
    private Integer id;
    private String specName;
    private Integer stockNum;
    private Double unitPrice;
    private Integer goodsId;

    public Spec() {
    }
    public Spec(SpecBO specBO,Integer goodsId) {
        this();
        setId(null);
        setSpecName(specBO.getSpecName());
        setStockNum(specBO.getStockNum());
        setUnitPrice(specBO.getUnitPrice());
        setGoodsId(goodsId);
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

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "Spec{" +
                "id=" + id +
                ", specName='" + specName + '\'' +
                ", stockNum=" + stockNum +
                ", unitPrice=" + unitPrice +
                ", goodsId=" + goodsId +
                '}';
    }
}
