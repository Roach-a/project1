package com.cskaoyan.project1.service;

import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.bo.GoodsAddBO;
import com.cskaoyan.project1.model.bo.TypeBO;
import com.cskaoyan.project1.model.vo.TypeGoodsVO;

import java.util.List;

public interface GoodsService {

    List<Type> getType();

    List<TypeGoodsVO> getGoodsByType(String typeId);

    void addGoods(GoodsAddBO goodsAddBO);

    boolean addGoodsType(TypeBO typeBO);
}
