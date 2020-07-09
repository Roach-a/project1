package com.cskaoyan.project1.dao.backstage;

import com.cskaoyan.project1.model.bo.backstage.orders.ChangeOrderBO;
import com.cskaoyan.project1.model.bo.backstage.orders.OrdersSearchBO;
import com.cskaoyan.project1.model.vo.backstage.PageOrdersInfoVO;
import com.cskaoyan.project1.model.vo.backstage.orders.OrdersEditVO;
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

    @Override
    public void changeOrder(ChangeOrderBO changeOrderBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        String sql1 = "update orders set num = ? ,goodsDetailId = ?,stateId=? where id = ?";
        //联表查询，通过规格id更新规格
        String sql2 = "  update orders set spec = \n" +
                "\t(\n" +
                "\tselect temp.specName from \n" +
                "\t\t\t(select specName from spec  join orders on goodsDetailId = spec.id where spec.id = orders.goodsDetailId) temp\n" +
                "\t)\n" +
                "\twhere id = ?;";
        try {
            runner.update(sql1,changeOrderBO.getNum(),changeOrderBO.getSpec(),changeOrderBO.getState(),changeOrderBO.getId());
            runner.update(sql2,changeOrderBO.getId());
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from orders where id = ?",id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
