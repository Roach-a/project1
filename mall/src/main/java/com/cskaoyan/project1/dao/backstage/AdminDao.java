package com.cskaoyan.project1.dao.backstage;

import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.bo.backstage.Admin.ChangePwdBo;

import java.util.List;

public interface AdminDao {
    Admin login(Admin admin) ;

    List<Admin> allAdmins();

    boolean addAdmin(Admin admin);

    void deleteAdmins(int id);

    Admin getAdminsInfo(int id);

    boolean updateAdminss(Admin admin);

    List<Admin> getSearchAdmins(Admin admin);

    boolean changePwd(ChangePwdBo changePwdBo);
}
