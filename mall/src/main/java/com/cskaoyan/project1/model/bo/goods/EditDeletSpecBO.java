package com.cskaoyan.project1.model.bo.goods;

import com.cskaoyan.project1.model.Spec;

public class EditDeletSpecBO {
    private Integer goodsId;
    private String specName;

    public EditDeletSpecBO() {
    }
    public Spec toSpec(){
        Spec spec = new Spec();
        spec.setGoodsId(goodsId);
        spec.setSpecName(specName);
        return spec;
    }

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
}
