package com.cskaoyan.project1.dao.backstage;

import com.cskaoyan.project1.model.User;

import java.util.List;

public interface UserDao {
        List<User> allUser();

    void deleteUser(int id);

    List<User> searchUser(String nick);

    User getUserInfoById(Integer userId);

    User getUserInfoByEmail(String email);

    boolean signup(User user);

    User login(User user);
}
