package com.cskaoyan.project1.model;

import com.cskaoyan.project1.model.bo.message.ReplyBO;

public class Message {
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private String content;
    private String replyContent;
    private Integer state;
    private String createtime;

    public Message() {
    }

    public Message(ReplyBO replyBO) {
        this.id = replyBO.getId();
        this.replyContent = replyBO.getContent();
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
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
}
