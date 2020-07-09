package com.cskaoyan.project1.service.backstage;

import com.cskaoyan.project1.dao.backstage.GoodsDaoImpl;
import com.cskaoyan.project1.dao.backstage.OrdersDao;
import com.cskaoyan.project1.dao.backstage.OrdersDaoImpl;
import com.cskaoyan.project1.model.Spec;
import com.cskaoyan.project1.model.bo.backstage.orders.ChangeOrderBO;
import com.cskaoyan.project1.model.bo.backstage.orders.OrdersSearchBO;
import com.cskaoyan.project1.model.vo.backstage.PageOrdersInfoVO;
import com.cskaoyan.project1.model.vo.backstage.PageOrdersVO;
import com.cskaoyan.project1.model.vo.backstage.orders.OrdersEditVO;
import com.cskaoyan.project1.model.vo.backstage.orders.OrdersSpecEditVO;
import com.cskaoyan.project1.model.vo.backstage.orders.OrdersStateVO;
import com.cskaoyan.project1.utils.OrdersStatesUtils;

import java.util.ArrayList;
import java.util.List;

public class OrdersServiceImpl implements OrdersService {
    OrdersDao ordersDao = new OrdersDaoImpl();
    //订单查询方法
    @Override
    public PageOrdersVO ordersByPage(OrdersSearchBO searchBO) {
        PageOrdersVO pageOrdersVO = new PageOrdersVO();
        Integer total = ordersDao.getOrdersTotal(searchBO);
        List<PageOrdersInfoVO> ordersInfoVOList = ordersDao.ordersByPage(searchBO);
        pageOrdersVO.setTotal(total);
        pageOrdersVO.setOrders(ordersInfoVOList);
        return pageOrdersVO;
    }
    //编辑订单时候，显示订单详情
    @Override
    public OrdersEditVO order(int orderId) {
        //获取大的对象
        OrdersEditVO ordersEditVO = ordersDao.order(orderId);
        //通过工具类获取states并赋值
        List<OrdersStateVO> stateVOS = OrdersStatesUtils.getOrderStatesList();
        ordersEditVO.setStates(stateVOS);
        //获取货物id
        Integer goodsId = ordersDao.getGoodsIdByOrderId(orderId);
        System.out.println(ordersEditVO);
        //获取规格并放到大对象里
        List<Spec> specList = new GoodsDaoImpl().getSpecList(goodsId);
        List<OrdersSpecEditVO> specEditVOList = new ArrayList<>();
        for (Spec spec:specList) {
            specEditVOList.add(new OrdersSpecEditVO(spec));
        }
        ordersEditVO.setSpec(specEditVOList);
        return ordersEditVO;
    }

    @Override
    public void changeOrder(ChangeOrderBO changeOrderBO) {
        ordersDao.changeOrder(changeOrderBO);
    }

    @Override
    public void deleteOrder(int id) {
        ordersDao.deleteOrder(id);
    }
}
