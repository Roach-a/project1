package com.cskaoyan.project1.dao.backstage;

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
    public User getUserInfoById(Integer userId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        User user = null;
        try {
            user = runner.query("select * from user where id = ?",new BeanHandler<User>(User.class),userId);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserInfoByEmail(String email) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        User user = null;
        try {
            user = runner.query("select * from user where email = ?",new BeanHandler<User>(User.class),email);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean signup(User user) {
        if (getUserInfoByEmail(user.getEmail()) != null)return false;
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "insert into user values (null,?,?,?,?,?,?)";
        Integer row = 0;
        try {
            row = runner.update(sql,user.getEmail(),user.getNickname(),user.getPwd(),user.getRecipient(),user.getAddress(),user.getPhone());
        }catch (SQLException e) {
            e.printStackTrace();
        }
        if (row == 1)return true;
        else return false;
    }
    //用户登陆
    @Override
    public User login(User user) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from user where email = ? and pwd = ?";
        User res = null;
        try {
            res = runner.query(sql,new BeanHandler<User>(User.class),user.getEmail(),user.getPwd());
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
