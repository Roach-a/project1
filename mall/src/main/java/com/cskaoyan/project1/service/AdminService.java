package com.cskaoyan.project1.service;

import com.cskaoyan.project1.model.bo.Admin;
import com.cskaoyan.project1.model.bo.AdminLoginBO;

public interface AdminService {
    Admin login(AdminLoginBO loginBO);
}
