package com.cskaoyan.project1.controller;

import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.bo.Admin;
import com.cskaoyan.project1.model.bo.AdminLoginBO;
import com.cskaoyan.project1.model.vo.AdminLoginVO;
import com.cskaoyan.project1.service.AdminService;
import com.cskaoyan.project1.service.AdminServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {

    private AdminService adminService = new AdminServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //当前servle可以处理登陆、查询所有admin、删除admin等操作
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/","");
        if ("login".equals(action)) {
            login(request,response);
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
        InputStream inputStream = request.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes,0,length);
        }
        //字符串转为 java 对象
        String requestBody = outputStream.toString();
        Gson gson = new Gson();
        AdminLoginBO loginBO = gson.fromJson(requestBody,AdminLoginBO.class);
        //System.out.println(loginBO);
        Admin login = adminService.login(loginBO);
        Result result = new Result();
        if (login != null) {
            result.setCode(0);
            AdminLoginVO loginVO = new AdminLoginVO();
            loginVO.setToken(login.getNickname());
            loginVO.setName(login.getNickname());
            result.setData(loginVO);
        }else {
            result.setCode(10000);
            result.setMessage("确认用户名和密码");
        }
        response.getWriter().println(gson.toJson(result));
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
