package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.User;

import java.util.List;

public interface UserDao {
    List<User> allUser();

    void deleteUser(int id);

    List<User> searchUser(String nick);

    User getUserInfo(Integer userId);
}
