package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.bo.orders.OrdersSearchBO;
import com.cskaoyan.project1.model.vo.PageOrdersInfoVO;
import com.cskaoyan.project1.model.vo.orders.OrdersEditVO;

import java.util.List;

public interface OrdersDao {

    Integer getOrdersTotal(OrdersSearchBO searchBO);

    List<PageOrdersInfoVO> ordersByPage(OrdersSearchBO searchBO);

    OrdersEditVO order(int orderId);

    Integer getGoodsIdByOrderId(int orderId);
}
