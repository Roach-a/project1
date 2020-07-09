package com.cskaoyan.project1.service.backstage;

import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.bo.backstage.Admin.AdminAddBO;
import com.cskaoyan.project1.model.bo.backstage.Admin.AdminLoginBO;
import com.cskaoyan.project1.model.bo.backstage.Admin.AdminSearchBO;
import com.cskaoyan.project1.model.bo.backstage.Admin.ChangePwdBo;

import java.util.List;

public interface AdminService {
    Admin login(AdminLoginBO loginBO);

    List<Admin> allAdmins();

    boolean addAdmin(AdminAddBO addBO);

    void deleteAdmins(int id);

    Admin getAdminsInfo(int id);

    boolean updateAdminss(Admin admin);

    List<Admin> getSearchAdmins(AdminSearchBO adminSearchBO);

    boolean changePwd(ChangePwdBo changePwdBo);
}
