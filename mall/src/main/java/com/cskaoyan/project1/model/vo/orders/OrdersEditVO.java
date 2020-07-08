package com.cskaoyan.project1.model.vo.orders;

import com.cskaoyan.project1.model.enumaration.OrderState;

import java.util.ArrayList;
import java.util.List;

public class OrdersEditVO {
    private Integer id;
    private Integer amount;
    private Integer num;
    private Integer goodsDetailId;
    private Integer state;
    private String goods;//goods名字
    private List<OrdersSpecEditVO> spec;
    private List<OrdersStateVO> states = new ArrayList<>();
    private OrdersEditCurStateVO curState = new OrdersEditCurStateVO();
    private OrdersEditCurSpecVO curSpec = new OrdersEditCurSpecVO();

    public OrdersEditVO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.curSpec.setId(goodsDetailId);
        this.goodsDetailId = goodsDetailId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.curState.setId(state);
        this.state = state;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public List<OrdersSpecEditVO> getSpec() {
        return spec;
    }

    public void setSpec(List<OrdersSpecEditVO> spec) {
        this.spec = spec;
    }

    public OrdersEditCurStateVO getCurState() {
        return curState;
    }


    public OrdersEditCurSpecVO getCurSpec() {
        return curSpec;
    }

    public List<OrdersStateVO> getStates() {
        return states;
    }

    public void setStates(List<OrdersStateVO> states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "OrdersEditVO{" +
                "id=" + id +
                ", amount=" + amount +
                ", num=" + num +
                ", goodsDetailId=" + goodsDetailId +
                ", state=" + state +
                ", goods='" + goods + '\'' +
                ", spec=" + spec +
                ", states=" + states +
                ", curState=" + curState +
                ", curSpec=" + curSpec +
                '}';
    }
}
