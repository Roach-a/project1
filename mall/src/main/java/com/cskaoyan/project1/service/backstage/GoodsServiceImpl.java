package com.cskaoyan.project1.service.backstage;

import com.cskaoyan.project1.dao.backstage.GoodsDao;
import com.cskaoyan.project1.dao.backstage.GoodsDaoImpl;
import com.cskaoyan.project1.dao.backstage.UserDaoImpl;
import com.cskaoyan.project1.model.Goods;
import com.cskaoyan.project1.model.Message;
import com.cskaoyan.project1.model.Spec;
import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.bo.backstage.goods.*;
import com.cskaoyan.project1.model.bo.backstage.message.ReplyBO;
import com.cskaoyan.project1.model.vo.backstage.*;

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

    @Override
    public GoodsInfoEditVO getGoodsInfo(int id) {
        //获取完整商品信息
        Goods good =  goodsDao.getGoodsInfo(id);
        //获取完整规格信息
        List<Spec> specList = goodsDao.getSpecList(id);
        //封装到GoodsInfoEditVO
        GoodsInfoEditVO goodsInfoEditVO = GoodsToGoodsInfoEditVO(good,specList);
        return goodsInfoEditVO;
    }
    //把取到的完整商品和商品规格信息封装到要求的GoodsInfoEditVO里
    private GoodsInfoEditVO GoodsToGoodsInfoEditVO(Goods good, List<Spec> specList) {
        GoodsSelfEditVO goodsSelfEditVO = new GoodsSelfEditVO(good);
        GoodsInfoEditVO goodsInfoEditVO = new GoodsInfoEditVO();
        List<GoodsSpecEditVO> specVOList = new ArrayList<>();
        for (Spec spec:specList) {
            specVOList.add(new GoodsSpecEditVO(spec));
        }
        goodsInfoEditVO.setSpecs(specVOList);
        goodsInfoEditVO.setGoodsSelfEditVO(goodsSelfEditVO);
        return goodsInfoEditVO;
    }

    @Override
    public void updateGoods(GoodsEditComfirmBO comfirmBO) {
        List<Spec> specList= new ArrayList<>();
        int goodsId = comfirmBO.getId();
        //把SpecList集合转换成Spec集合,并计算应该需要的价格
        for (GoodsEditComfirmSpecBO comfirmSpecBO:comfirmBO.getSpecList() ) {
            specList.add(comfirmSpecBO.toSpec(goodsId));
        }
        Double[] priceAndStocknum = goodsDao.updateSpecs(specList);
        Goods goods;
        //GoodsEditComfirmBO转换成Goods，并更新价格和库存
        goods = comfirmBO.toGoods(priceAndStocknum);
        goodsDao.updateGoods(goods);
    }

    @Override
    public boolean addSpec(EditAddSpecBO editAddSpecBO) {
        return goodsDao.addSpec(editAddSpecBO.toSpec());

    }

    @Override
    public void deleteSpec(EditDeletSpecBO deletSpecBO) {
        Spec spec = deletSpecBO.toSpec();
        goodsDao.deleteSpec(spec);
    }

    @Override
    public void deleteGoods(int id) {
        goodsDao.deleteGoods(id);
    }

    @Override
    public List<MessageNoReplyVO> noReplyMsg() {
        List<Message> messageList = goodsDao.noReplyMsg();
        List<MessageNoReplyVO> messageNoReplyVOList = new ArrayList<>();
        for (Message message : messageList) {
            MessageNoReplyVO messageNoReplyVO = new MessageNoReplyVO(message);
            Message_goodsVO goodsVO = new Message_goodsVO(goodsDao.getGoodsInfo(message.getGoodsId()));
            Message_userVO userVO = new Message_userVO(new UserDaoImpl().getUserInfoById(message.getUserId()));
            messageNoReplyVO.setGoods(goodsVO);
            messageNoReplyVO.setUser(userVO);
            messageNoReplyVOList.add(messageNoReplyVO);
        }
        return messageNoReplyVOList;
    }

    @Override
    public List<MessageRepliedVO> repliedMsg() {
        List<Message> messageList = goodsDao.repliedMsg();
        List<MessageRepliedVO> messageRepliedVOList = new ArrayList<>();
        for (Message message : messageList) {
            MessageRepliedVO messageRepliedVO = new MessageRepliedVO(message);
            Message_goodsVO goodsVO = new Message_goodsVO(goodsDao.getGoodsInfo(message.getGoodsId()));
            Message_userVO userVO = new Message_userVO(new UserDaoImpl().getUserInfoById(message.getUserId()));
            messageRepliedVO.setGoods(goodsVO);
            messageRepliedVO.setUser(userVO);
            messageRepliedVOList.add(messageRepliedVO);
        }
        return messageRepliedVOList;
    }


    @Override
    public void reply(ReplyBO replyBO) {
        Message message = new Message(replyBO);
        goodsDao.reply(message);

    }

    @Override
    public void deleteType(Integer typeId) {
        List<Integer> goodsIdList = goodsDao.getGoodsIdByType(typeId);
        if (goodsIdList != null) {
            for (Integer goodId:goodsIdList) {
                goodsDao.deleteGoods(goodId);
            }
        }
        goodsDao.deleteType(typeId);
    }
}
