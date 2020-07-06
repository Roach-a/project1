package com.cskaoyan.project1.service;

import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.bo.AdminAddBO;
import com.cskaoyan.project1.model.bo.AdminLoginBO;
import com.cskaoyan.project1.model.bo.AdminSearchBO;

import java.util.List;

public interface AdminService {
    Admin login(AdminLoginBO loginBO);

    List<Admin> allAdmins();

    boolean addAdmin(AdminAddBO addBO);

    void deleteAdmins(int id);

    Admin getAdminsInfo(int id);

    boolean updateAdminss(Admin admin);

    List<Admin> getSearchAdmins(AdminSearchBO adminSearchBO);
}
