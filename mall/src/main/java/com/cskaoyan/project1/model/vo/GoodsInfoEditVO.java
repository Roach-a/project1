package com.cskaoyan.project1.model.vo;

import com.cskaoyan.project1.model.Goods;

import java.util.List;

/**
 * 编辑商品页面返回的商品对象
 */
public class GoodsInfoEditVO {
    List<GoodsSpecEditVO> specs;
    GoodsSelfEditVO goods;


    public GoodsInfoEditVO() {
    }
    public List<GoodsSpecEditVO> getSpecs() {
        return specs;
    }

    public void setSpecs(List<GoodsSpecEditVO> specs) {
        this.specs = specs;
    }

    public GoodsSelfEditVO getGoodsSelfEditVO() {
        return goods;
    }

    public void setGoodsSelfEditVO(GoodsSelfEditVO goodsSelfEditVO) {
        this.goods = goodsSelfEditVO;
    }

    @Override
    public String toString() {
        return "GoodsInfoEditVO{" +
                "specs=" + specs +
                ", goodsSelfEditVO=" + goods +
                '}';
    }
}
