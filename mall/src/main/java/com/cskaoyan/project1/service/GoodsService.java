package com.cskaoyan.project1.service;

import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.bo.goods.*;
import com.cskaoyan.project1.model.bo.message.ReplyBO;
import com.cskaoyan.project1.model.vo.GoodsInfoEditVO;
import com.cskaoyan.project1.model.vo.MessageNoReplyVO;
import com.cskaoyan.project1.model.vo.MessageRepliedVO;
import com.cskaoyan.project1.model.vo.TypeGoodsVO;

import java.util.List;

public interface GoodsService {

    List<Type> getType();

    List<TypeGoodsVO> getGoodsByType(String typeId);

    void addGoods(GoodsAddBO goodsAddBO);

    boolean addGoodsType(TypeBO typeBO);

    GoodsInfoEditVO getGoodsInfo(int id);

    void updateGoods(GoodsEditComfirmBO comfirmBO);

    boolean addSpec(EditAddSpecBO editAddSpecBO);

    void deleteSpec(EditDeletSpecBO deletSpecBO);

    void deleteGoods(int id);

    List<MessageNoReplyVO> noReplyMsg();

    List<MessageRepliedVO> repliedMsg();

    void reply(ReplyBO replyBO);

    void deleteType(Integer typeId);
}
