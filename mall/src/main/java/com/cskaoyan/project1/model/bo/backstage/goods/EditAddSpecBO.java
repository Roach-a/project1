package com.cskaoyan.project1.model.bo.backstage.goods;

import com.cskaoyan.project1.model.Spec;

public class EditAddSpecBO {
    private Integer goodsId;
    private String specName;
    private Integer stockNum;
    private Double unitPrice;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
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

    public Spec toSpec() {
        Spec spec = new Spec(specName,stockNum,unitPrice,goodsId);
        return spec;
    }
}
