package com.cskaoyan.project1.service.backstage;

import com.cskaoyan.project1.dao.backstage.AdminDao;
import com.cskaoyan.project1.dao.backstage.AdminDaoImpl;
import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.bo.backstage.Admin.AdminAddBO;
import com.cskaoyan.project1.model.bo.backstage.Admin.AdminLoginBO;
import com.cskaoyan.project1.model.bo.backstage.Admin.AdminSearchBO;
import com.cskaoyan.project1.model.bo.backstage.Admin.ChangePwdBo;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();
    private Admin admin = new Admin();
    @Override
    public Admin login(AdminLoginBO loginBO) {
        admin.setEmail(loginBO.getEmail());
        admin.setPwd(loginBO.getPwd());

        return adminDao.login(admin);
    }

    @Override
    public List<Admin> allAdmins() {
        return adminDao.allAdmins();
    }

    @Override
    public boolean addAdmin(AdminAddBO addBO) {
        admin.setEmail(addBO.getEmail());
        admin.setPwd(addBO.getPwd());
        admin.setNickname(addBO.getNickname());
        return adminDao.addAdmin(admin);
    }

    @Override
    public void deleteAdmins(int id) {
        adminDao.deleteAdmins(id);
    }

    @Override
    public Admin getAdminsInfo(int id) {
        return adminDao.getAdminsInfo(id);
    }

    @Override
    public boolean updateAdminss(Admin admin) {
        return adminDao.updateAdminss(admin);
    }

    @Override
    public List<Admin> getSearchAdmins(AdminSearchBO adminSearchBO) {
        admin.setNickname(adminSearchBO.getNickname());
        admin.setEmail(adminSearchBO.getEmail());
        return adminDao.getSearchAdmins(admin);
    }

    @Override
    public boolean changePwd(ChangePwdBo changePwdBo) {

        return adminDao.changePwd(changePwdBo);
    }
}
