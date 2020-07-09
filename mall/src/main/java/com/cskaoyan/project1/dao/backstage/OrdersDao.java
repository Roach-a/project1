package com.cskaoyan.project1.dao.backstage;

import com.cskaoyan.project1.model.bo.backstage.orders.ChangeOrderBO;
import com.cskaoyan.project1.model.bo.backstage.orders.OrdersSearchBO;
import com.cskaoyan.project1.model.vo.backstage.PageOrdersInfoVO;
import com.cskaoyan.project1.model.vo.backstage.orders.OrdersEditVO;

import java.util.List;

public interface OrdersDao {

    Integer getOrdersTotal(OrdersSearchBO searchBO);

    List<PageOrdersInfoVO> ordersByPage(OrdersSearchBO searchBO);

    OrdersEditVO order(int orderId);

    Integer getGoodsIdByOrderId(int orderId);

    void changeOrder(ChangeOrderBO changeOrderBO);

    void deleteOrder(int id);
}
