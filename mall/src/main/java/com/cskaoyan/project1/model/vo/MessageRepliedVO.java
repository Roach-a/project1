package com.cskaoyan.project1.model.vo;

import com.cskaoyan.project1.model.Message;

public class MessageRepliedVO {
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private String content;
    private String replyContent;
    private Integer state;
    private String createtime;
    private Message_goodsVO goods;
    private Message_userVO user;

    public MessageRepliedVO(Message message) {
        this.id = message.getId();
        this.userId = message.getUserId();
        this.goodsId = message.getGoodsId();
        this.content = message.getContent();
        this.replyContent = message.getReplyContent();
        this.state = message.getState();
        this.createtime = message.getCreatetime();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Message_goodsVO getGoods() {
        return goods;
    }

    public void setGoods(Message_goodsVO goods) {
        this.goods = goods;
    }

    public Message_userVO getUser() {
        return user;
    }

    public void setUser(Message_userVO user) {
        this.user = user;
    }
}
