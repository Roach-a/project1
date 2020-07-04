package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.bo.Admin;
import com.cskaoyan.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    private QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
    @Override
    public Admin login(Admin admin) {

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
        try {
            runner.update("delete from admin where id = ?", id);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Admin getAdminsInfo(int id) {
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
}
