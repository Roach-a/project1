package com.cskaoyan.project1.model.bo.backstage.goods;

import com.cskaoyan.project1.model.Spec;

public class GoodsEditComfirmSpecBO {
    private Integer id;
    private String specName;
    private Integer stockNum;
    private Double unitPrice;

    public Integer getId() {
        return id;
    }
    public Spec toSpec(Integer goodsId){
        Spec spec = new Spec(specName,stockNum,unitPrice,goodsId);
        spec.setId(id);
        return spec;
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
