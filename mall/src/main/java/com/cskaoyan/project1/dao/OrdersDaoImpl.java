package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.bo.orders.OrdersSearchBO;
import com.cskaoyan.project1.model.vo.PageOrdersInfoVO;
import com.cskaoyan.project1.model.vo.orders.OrdersEditVO;
import com.cskaoyan.project1.utils.DruidUtils;
import com.cskaoyan.project1.utils.DynamicSQLUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrdersDaoImpl implements OrdersDao {
    @Override
    public Integer getOrdersTotal(OrdersSearchBO searchBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Map<String,Object> map = DynamicSQLUtils.searchOrdersByPage(searchBO);
        String sql = "select count(*) from orders";
        sql += map.get("sql");
        List<Object> params = (List<Object>)map.get("params");
        Number total = null;
        try {
            total = runner.query(sql,new ScalarHandler<>(),params.toArray());
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return total.intValue();
    }

    @Override
    public List<PageOrdersInfoVO> ordersByPage(OrdersSearchBO searchBO) {
        Map<String,Object> map = DynamicSQLUtils.searchOrdersByPage(searchBO);
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select id,userId,goodsDetailId,goods,spec,num as goodsNum,amount"+
                ",stateId,nickname,name,address,phone from orders ";
        sql += map.get("sql");
        List<Object> params = (List<Object>)map.get("params");
        //处理页数相关逻辑
        sql += "limit ? offset ?";
        params.add(searchBO.getPagesize());
        params.add((searchBO.getCurrentPage()-1)*searchBO.getPagesize());
        //得到结果List
        List<PageOrdersInfoVO> ordersInfoVOList = null;
        try {
            ordersInfoVOList = runner.query(sql,new BeanListHandler<PageOrdersInfoVO>(PageOrdersInfoVO.class),params.toArray());
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersInfoVOList;
    }
    //查询订单详情，先查询订单有的东西，id,amount,goodsNum as num,goodsDetailId,state,goods,state,goods
    @Override
    public OrdersEditVO order(int orderId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        OrdersEditVO ordersEditVO = null;
        String sql = "select id,amount,num,goodsDetailId,goods,stateId as state,goods from orders where id = ?";
        try {
            ordersEditVO = runner.query(sql,new BeanHandler<OrdersEditVO>(OrdersEditVO.class),orderId);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersEditVO;
    }
    //根据订单ID获取商品id
    @Override
    public Integer getGoodsIdByOrderId(int orderId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Number goodsId = null;
        try {
            goodsId = runner.query("select goodsId from orders where id = ?",new ScalarHandler<>(),orderId);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsId.intValue();
    }
}
