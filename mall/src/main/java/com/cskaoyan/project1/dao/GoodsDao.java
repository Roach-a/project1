package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.Goods;
import com.cskaoyan.project1.model.Spec;
import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.bo.TypeBO;
import com.cskaoyan.project1.model.vo.TypeGoodsVO;

import java.util.List;

public interface GoodsDao {
    List<Type> getType();

    List<TypeGoodsVO> getGoodsByType(String typeId);

    void addGoods(Goods goods);

    int lastInsertId();

    void addSpecs(List<Spec> specs);

    boolean addGoodsType(Type type);
}
