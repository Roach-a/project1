package com.cskaoyan.project1.service.backstage;

import com.cskaoyan.project1.model.User;
import com.cskaoyan.project1.model.bo.foreground.UserLoginBO;
import com.cskaoyan.project1.model.bo.foreground.UserRegisterBO;

import java.util.List;

public interface UserService {
    List<User> allUser();

    void deleteUser(int id);

    List<User> searchUser(String nick);

    boolean signup(UserRegisterBO registerBO);

    User login(UserLoginBO loginBO);
}
