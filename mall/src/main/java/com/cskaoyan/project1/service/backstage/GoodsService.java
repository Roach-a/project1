package com.cskaoyan.project1.service.backstage;

import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.bo.backstage.goods.*;
import com.cskaoyan.project1.model.bo.backstage.message.ReplyBO;
import com.cskaoyan.project1.model.vo.backstage.GoodsInfoEditVO;
import com.cskaoyan.project1.model.vo.backstage.MessageNoReplyVO;
import com.cskaoyan.project1.model.vo.backstage.MessageRepliedVO;
import com.cskaoyan.project1.model.vo.backstage.TypeGoodsVO;

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
