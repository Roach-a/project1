package com.cskaoyan.project1.utils;

import com.alibaba.druid.util.StringUtils;
import com.cskaoyan.project1.model.Admin;

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
}
