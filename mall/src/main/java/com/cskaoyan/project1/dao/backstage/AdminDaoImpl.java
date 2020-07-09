package com.cskaoyan.project1.dao.backstage;

import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.bo.backstage.Admin.ChangePwdBo;
import com.cskaoyan.project1.utils.DruidUtils;
import com.cskaoyan.project1.utils.DynamicSQLUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AdminDaoImpl implements AdminDao {

    @Override
    public Admin login(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin query = null;
        try {
            query = runner.query("select * from admin where email = ? and pwd = ?",
                    new BeanHandler<>(Admin.class),admin.getEmail(),admin.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;

    }

    @Override
    public List<Admin> allAdmins() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Admin> admins = null;
        try {
            admins = runner.query("select * from admin", new BeanListHandler<Admin>(Admin.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }

    @Override
    public boolean addAdmin(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        System.out.println("adminDao_addAdmin");
        Admin query = null;
        try {
            query = runner.query("select * from admin where email = ?",new BeanHandler<Admin>(Admin.class),admin.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (query != null)return false;
        int row = 0;
        try {
            row = runner.update("insert into admin(email,pwd,nickname) values (?,?,?)"
                    ,admin.getEmail(),admin.getPwd(),admin.getNickname());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (row == 1)return true;
        else {
            System.out.println("SQL查询错误");
            return false;
        }
    }

    @Override
    public void deleteAdmins(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from admin where id = ?", id);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Admin getAdminsInfo(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin admin = null;
        try {
            admin = runner.query("select * from admin where id = ?"
                    ,new BeanHandler<Admin>(Admin.class),id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public boolean updateAdminss(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        int id = admin.getId();

        int row = 0;
        try{
            row = runner.update("update admin set email = ?,pwd = ?,nickname = ? where id = ?"
                    ,admin.getEmail(),admin.getPwd(),admin.getNickname(),admin.getId());
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return row == 1;
    }

    @Override
    public List<Admin> getSearchAdmins(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Map<String,Object> map = DynamicSQLUtils.searchAdmins(admin);
        String sql = (String)map.get("sql");
        List<String> params = (List<String>)map.get("params");
        List<Admin> list = null;
        try {
            list = runner.query(sql, new BeanListHandler<Admin>(Admin.class), params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean changePwd(ChangePwdBo changePwdBo) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        String  sql = "update admin set pwd = ? where nickname = ? and pwd = ?";
        int row = 0;
        try {
           row = runner.update(sql,changePwdBo.getNewPwd(),changePwdBo.getAdminToken(),changePwdBo.getOldPwd());
        }catch (SQLException e){
            e.printStackTrace();
        }
        if (row == 0)return false;
        return true;
    }
}
