package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.User;
import com.cskaoyan.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> allUser() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<User> userList = null;
        try{
            userList = runner.query("select * from user",new BeanListHandler<User>(User.class));
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void deleteUser(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try{
            runner.update("delete from user where id = ?",id);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> searchUser(String nick) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<User> userList = null;
        try{
            userList = runner.query("select * from user where nickname like ?", new BeanListHandler<User>(User.class)
                    , "%" + nick + "%");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getUserInfo(Integer userId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        User user = null;
        try {
            user = runner.query("select * from user where id = ?",new BeanHandler<User>(User.class),userId);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
