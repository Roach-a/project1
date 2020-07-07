package com.cskaoyan.project1.service;

import com.cskaoyan.project1.dao.UserDao;
import com.cskaoyan.project1.dao.UserDaoImpl;
import com.cskaoyan.project1.model.User;

import java.util.List;

public class UserServiceImpl implements UserService{
    UserDao userDao = new UserDaoImpl();
    @Override
    public List<User> allUser() {
        return userDao.allUser();
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public List<User> searchUser(String nick) {
        return userDao.searchUser(nick);
    }
}
