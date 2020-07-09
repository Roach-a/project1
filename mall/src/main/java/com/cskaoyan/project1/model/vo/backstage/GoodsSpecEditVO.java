package com.cskaoyan.project1.model.vo.backstage;

import com.cskaoyan.project1.model.Spec;

/**
 * 编辑商品页面返回的规格对象(只比SpecBo多一个id）
 */
public class GoodsSpecEditVO {
    private Integer id;
    private String specName;
    private Integer stockNum;
    private Double unitPrice;

    public GoodsSpecEditVO() {}
    public GoodsSpecEditVO(Spec spec) {
        id = spec.getId();
        specName = spec.getSpecName();
        stockNum = spec.getStockNum();
        unitPrice = spec.getUnitPrice();
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

}
