package com.cskaoyan.project1.service.backstage;

import com.cskaoyan.project1.model.bo.backstage.orders.ChangeOrderBO;
import com.cskaoyan.project1.model.bo.backstage.orders.OrdersSearchBO;
import com.cskaoyan.project1.model.vo.backstage.PageOrdersVO;
import com.cskaoyan.project1.model.vo.backstage.orders.OrdersEditVO;

public interface OrdersService {
    PageOrdersVO ordersByPage(OrdersSearchBO searchBO);
    OrdersEditVO order(int orderId);
    void changeOrder(ChangeOrderBO changeOrderBO);

    void deleteOrder(int id);
}
