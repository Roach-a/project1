package com.cskaoyan.project1.utils;

import com.alibaba.druid.util.StringUtils;
import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.bo.orders.OrdersSearchBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicSQLUtils {
    static Map<String,Object> map;

    public static Map<String,Object> searchAdmins(Admin admin) {
        map = new HashMap<>();
        List<String> params = new ArrayList<>();
        String sql = "select * from admin where 1 = 1 ";
        if (!StringUtils.isEmpty(admin.getEmail())) {
            sql += "and email like ? ";
            params.add("%"+admin.getEmail()+"%");
        }
        if (!StringUtils.isEmpty(admin.getNickname())) {
            sql += "and nickname like ?";
            params.add("%"+admin.getNickname()+"%");
        }
        map.put("sql",sql);
        map.put("params",params);
        return map;
    }
    public static Map<String,Object> searchOrdersByPage(OrdersSearchBO searchBO) {
        map = new HashMap<>();
        String searchConditionSQL = " where 1 = 1 ";
        List<Object> params = new ArrayList<>();
        if (!StringUtils.isEmpty(searchBO.getId())) {
            searchConditionSQL += "and id = ? ";
            params.add(searchBO.getId());
        }
        if (!StringUtils.isEmpty(searchBO.getGoods())){
            searchConditionSQL += "and goods like ? ";
            params.add("%"+searchBO.getGoods()+"%");
        }
        if (!StringUtils.isEmpty(searchBO.getAddress())) {
            searchConditionSQL += "and address like ? ";
            params.add("%"+searchBO.getAddress()+"%");
        }
        if (!StringUtils.isEmpty(searchBO.getMoneyLimit1())) {
            searchConditionSQL += "and amount >= ? ";
            params.add(searchBO.getMoneyLimit1());
        }
        if (!StringUtils.isEmpty(searchBO.getMoneyLimit2())) {
            searchConditionSQL += "and amount <= ? ";
            params.add(searchBO.getMoneyLimit2());
        }
        if (!StringUtils.isEmpty(searchBO.getName())) {
            searchConditionSQL += "and name like ? ";
            params.add("%"+searchBO.getName()+"%");
        }
        if (searchBO.getState()!= -1) {
            searchConditionSQL += "and state = ? ";
            params.add(searchBO.getState());
        }
        map.put("sql",searchConditionSQL);
        map.put("params",params);
        return map;
    }
}
