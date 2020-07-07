package com.cskaoyan.project1.service;

import com.cskaoyan.project1.model.User;

import java.util.List;

public interface UserService {
    List<User> allUser();

    void deleteUser(int id);

    List<User> searchUser(String nick);
}
