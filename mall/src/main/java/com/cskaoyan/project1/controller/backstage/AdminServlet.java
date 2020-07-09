package com.cskaoyan.project1.controller.backstage;

import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.bo.backstage.Admin.AdminAddBO;
import com.cskaoyan.project1.model.bo.backstage.Admin.AdminLoginBO;
import com.cskaoyan.project1.model.bo.backstage.Admin.AdminSearchBO;
import com.cskaoyan.project1.model.bo.backstage.Admin.ChangePwdBo;
import com.cskaoyan.project1.model.vo.backstage.AdminLoginVO;
import com.cskaoyan.project1.service.backstage.AdminService;
import com.cskaoyan.project1.service.backstage.AdminServiceImpl;
import com.cskaoyan.project1.utils.HttptUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {

    private AdminService adminService = new AdminServiceImpl();
    Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("did doPost");
        //当前servle可以处理登陆、查询所有admin、删除admin等操作
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/","");
        if ("login".equals(action)) {
            login(request,response);
        } else if ("addAdminss".equals(action)) {
            addAdminss(request,response);
        } else if ("updateAdminss".equals(action)) {
            updateAdminss(request,response);
        } else if ("getSearchAdmins".equals(action)) {
            getSearchAdmins(request,response);
        } else if ("logoutAdmin".equals(action)){
            logoutAdmin(request,response);
        } else if ("changePwd".equals(action)) {
            changePwd(request,response);
        }
    }

    private void changePwd(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String requestBody = HttptUtils.getRequestBody(request);
        ChangePwdBo changePwdBo = gson.fromJson(requestBody,ChangePwdBo.class);
        if (changePwdBo.getOldPwd().equals(changePwdBo.getNewPwd())){
            response.getWriter().println(gson.toJson(Result.error("新旧密码不能相同")));
        }else if (!changePwdBo.getConfirmPwd().equals(changePwdBo.getNewPwd())){
            response.getWriter().println(gson.toJson(Result.error("前后输入不一致，请确认")));
        }
        boolean res = adminService.changePwd(changePwdBo);
        if (res) {
            response.getWriter().println(gson.toJson(Result.ok()));
        }else {
            response.getWriter().println(gson.toJson(Result.error("密码错误")));
        }
    }

    private void logoutAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.getSession().removeAttribute("admin");
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * 简单多条件查询(底层使用动态sql思想)
     * @param request
     * @param response
     */
    private void getSearchAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String requestBody = HttptUtils.getRequestBody(request);
        AdminSearchBO adminSearchBO = gson.fromJson(requestBody,AdminSearchBO.class);
        List<Admin> data = adminService.getSearchAdmins(adminSearchBO);
        response.getWriter().println(gson.toJson(Result.ok(data)));
    }

    /**
     * 更新管理员信息
     * @param request
     * @param response
     * @throws IOException
     */
    private void updateAdminss(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String requestBody = HttptUtils.getRequestBody(request);
        Admin admin = gson.fromJson(requestBody,Admin.class);
        boolean result = adminService.updateAdminss(admin);
        if (result)response.getWriter().println(gson.toJson(Result.ok("修改管理员账号成功")));
        else response.getWriter().println(gson.toJson(Result.ok("修改失败")));
    }
    /**
     * 添加管理员账号
     * @param request
     * @param response
     */
    private void addAdminss(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttptUtils.getRequestBody(request);
        AdminAddBO adminAddBO = gson.fromJson(requestBody,AdminAddBO.class);
        boolean addAdminResult = adminService.addAdmin(adminAddBO);
        if (addAdminResult) {
            response.getWriter().println(gson.toJson(Result.ok("新增管理员成功")));
        } else {
            response.getWriter().println(gson.toJson(Result.error("插入管理员失败，已存在此用户")));
        }
    }

    /**
     * 管理员登陆逻辑
     * 1.浏览器向8084发送请求，携带json字符串
     * 2.查询数据库，校验用户名密码是否正确
     * 3.根据结果集返回不同的响应
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void login(HttpServletRequest request,HttpServletResponse response) throws IOException{
        //取出请求参数--请求体里的数据
        //无法使用request.getParameter
        String requestBody = HttptUtils.getRequestBody(request);
        AdminLoginBO loginBO = gson.fromJson(requestBody,AdminLoginBO.class);
        //System.out.println(loginBO);
        Admin login = adminService.login(loginBO);
        if (login != null) {
            AdminLoginVO loginVO = new AdminLoginVO();
            loginVO.setToken(login.getNickname());
            loginVO.setName(login.getNickname());
            response.getWriter().println(gson.toJson(Result.ok(loginVO)));

            request.getSession().setAttribute("admin",login);
        }else {
            response.getWriter().println(gson.toJson(Result.error("用户名或密码错误")));
        }

    }
    //*****************************************************************************************************************************
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("did doGet");
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/","");
        if ("allAdmins".equals(action)) {
            allAdmins(request,response);
        } else if ("deleteAdmins".equals(action)) {
            deleteAdmins(request,response);
        } else if ("getAdminsInfo".equals(action)) {
            getAdminsInfo(request,response);
        }
    }
    private void getAdminsInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
            int id = Integer.valueOf(request.getParameter("id"));
            response.getWriter().println(gson.toJson(Result.ok(adminService.getAdminsInfo(id))));
    }
    /**
     * 删除管理员账户
     * @param request
     * @param response
     */
    private void deleteAdmins(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int id = Integer.valueOf(request.getParameter("id"));

        adminService.deleteAdmins(id);
        response.setHeader("refresh","1,url = /api/admin/admin/allAdmins");
        response.getWriter().println(gson.toJson(Result.ok("删除成功")));
    }
    /**
     * 显示所有的admin账户信息
     * 1.查询数据库，返回数据
     * 2.做出响应
     * @param request
     * @param response
     */
    private void allAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Admin> adminList = adminService.allAdmins();
        response.getWriter().println(gson.toJson(Result.ok(adminList)));
    }
}
