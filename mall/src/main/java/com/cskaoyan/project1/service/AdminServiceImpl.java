package com.cskaoyan.project1.service;

import com.cskaoyan.project1.dao.AdminDao;
import com.cskaoyan.project1.dao.AdminDaoImpl;
import com.cskaoyan.project1.model.bo.Admin;
import com.cskaoyan.project1.model.bo.AdminLoginBO;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();
    @Override
    public Admin login(AdminLoginBO loginBO) {
        Admin admin = new Admin();
        admin.setEmail(loginBO.getEmail());
        admin.setPwd(loginBO.getPwd());

        return adminDao.login(admin);
    }
}
