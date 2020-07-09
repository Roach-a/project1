package com.cskaoyan.project1.model.bo.backstage.orders;

/**
 * 改变订单时的post请求报文内容
 */
public class ChangeOrderBO {
    private String id;
    private Integer num;
    private Integer spec;
    private Integer state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getSpec() {
        return spec;
    }

    public void setSpec(Integer spec) {
        this.spec = spec;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
