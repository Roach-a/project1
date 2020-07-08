package com.cskaoyan.project1.service;

import com.cskaoyan.project1.model.bo.orders.OrdersSearchBO;
import com.cskaoyan.project1.model.vo.PageOrdersVO;
import com.cskaoyan.project1.model.vo.orders.OrdersEditVO;

public interface OrdersService {
    PageOrdersVO ordersByPage(OrdersSearchBO searchBO);
    OrdersEditVO order(int orderId);
}
