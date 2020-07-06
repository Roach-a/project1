package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.Admin;

import java.util.List;

public interface AdminDao {
    Admin login(Admin admin) ;

    List<Admin> allAdmins();

    boolean addAdmin(Admin admin);

    void deleteAdmins(int id);

    Admin getAdminsInfo(int id);

    boolean updateAdminss(Admin admin);

    List<Admin> getSearchAdmins(Admin admin);
}
