package com.cskaoyan.project1.service.backstage;

import com.cskaoyan.project1.dao.backstage.UserDao;
import com.cskaoyan.project1.dao.backstage.UserDaoImpl;
import com.cskaoyan.project1.model.User;
import com.cskaoyan.project1.model.bo.foreground.UserLoginBO;
import com.cskaoyan.project1.model.bo.foreground.UserRegisterBO;

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

    @Override
    public boolean signup(UserRegisterBO registerBO) {
        User user = registerBO.toUser();
        return userDao.signup(user);
    }

    @Override
    public User login(UserLoginBO loginBO) {
        User user = loginBO.toUser();
        return userDao.login(user);
    }
}
