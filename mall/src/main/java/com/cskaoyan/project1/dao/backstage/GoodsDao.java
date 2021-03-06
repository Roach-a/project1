package com.cskaoyan.project1.dao.backstage;

import com.cskaoyan.project1.model.Goods;
import com.cskaoyan.project1.model.Spec;
import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.Message;
import com.cskaoyan.project1.model.vo.backstage.TypeGoodsVO;

import java.util.List;

public interface GoodsDao {
    List<Type> getType();

    List<TypeGoodsVO> getGoodsByType(String typeId);

    void addGoods(Goods goods);

    int lastInsertId();

    void addSpecs(List<Spec> specs);

    boolean addGoodsType(Type type);

    Goods getGoodsInfo(int id);

    List<Spec> getSpecList(int goodId);

    Double[] updateSpecs(List<Spec> specList);
    void updateGoods(Goods goods);

    void deleteSpec(Spec spec);

    boolean addSpec(Spec spec);

    void deleteGoods(int id);

    List<Message> noReplyMsg();

    List<Message> repliedMsg();

    void reply(Message message);

    List<Integer> getGoodsIdByType(Integer typeId);

    void deleteType(Integer typeId);
}
