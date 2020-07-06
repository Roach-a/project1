package com.cskaoyan.project1.service;

import com.cskaoyan.project1.dao.GoodsDao;
import com.cskaoyan.project1.dao.GoodsDaoImpl;
import com.cskaoyan.project1.model.Goods;
import com.cskaoyan.project1.model.Spec;
import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.bo.GoodsAddBO;
import com.cskaoyan.project1.model.bo.SpecBO;
import com.cskaoyan.project1.model.bo.TypeBO;
import com.cskaoyan.project1.model.vo.TypeGoodsVO;

import java.util.ArrayList;
import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    GoodsDao goodsDao = new GoodsDaoImpl();
    @Override
    public List<Type> getType() {
        return goodsDao.getType();
    }

    @Override
    public List<TypeGoodsVO> getGoodsByType(String typeId) {
        return goodsDao.getGoodsByType(typeId);
    }

    @Override
    public void addGoods(GoodsAddBO goodsAddBO) {
        Goods goods = new Goods(goodsAddBO);
        goodsDao.addGoods(goods);
        int id = goodsDao.lastInsertId();
        //保存到规格表
        List<SpecBO> specBOs = goodsAddBO.getSpecList();
        List<Spec> specs = new ArrayList<>();
        for (SpecBO specBO:specBOs) {
            specs.add(new Spec(specBO,id));
        }
        goodsDao.addSpecs(specs);
    }

    @Override
    public boolean addGoodsType(TypeBO typeBO) {
        Type type = new Type();
        type.setName(typeBO.getName());
        return goodsDao.addGoodsType(type);
    }
}
