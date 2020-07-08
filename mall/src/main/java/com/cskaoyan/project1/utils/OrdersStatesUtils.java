package com.cskaoyan.project1.utils;

import com.cskaoyan.project1.model.enumaration.OrderState;
import com.cskaoyan.project1.model.vo.orders.OrdersStateVO;

import java.util.ArrayList;
import java.util.List;

public class OrdersStatesUtils {
    public static List<OrdersStateVO> getOrderStatesList(){
        List<OrdersStateVO> stateList = new ArrayList<>();
        stateList.add(new OrdersStateVO(OrderState.UN_PAID) );
        stateList.add(new OrdersStateVO(OrderState.UN_SHIPED) );
        stateList.add(new OrdersStateVO(OrderState.DELIVERED) );
        stateList.add(new OrdersStateVO(OrderState.RECEIVED) );
        return stateList;
    }
}
