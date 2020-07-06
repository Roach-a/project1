package com.cskaoyan.project1.model;

import com.cskaoyan.project1.model.bo.GoodsAddBO;
import com.cskaoyan.project1.model.bo.SpecBO;

public class Goods {
    private Integer id;
    private String img;
    private String name;
    private Double price;
    private Integer typeId;
    private Integer stockNum;
    private String desc;

    public Goods() {
    }
    public Goods(GoodsAddBO goodsAddBO) {
        this();
        setId(null);
        setImg(goodsAddBO.getImg());
        setName(goodsAddBO.getName());
        setTypeId(goodsAddBO.getTypeId());
        setDesc(goodsAddBO.getDesc());
        double price = Integer.MAX_VALUE;
        int stockNum = Integer.MIN_VALUE;
        for (SpecBO specBO:goodsAddBO.getSpecList()) {
            price = Math.min(price,specBO.getUnitPrice());
            stockNum = Math.max(stockNum,specBO.getStockNum());
        }
        setPrice(price);
        setStockNum(stockNum);
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
