package com.cskaoyan.project1.controller.foreground;

import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.User;
import com.cskaoyan.project1.model.bo.foreground.UserLoginBO;
import com.cskaoyan.project1.model.bo.foreground.UserRegisterBO;
import com.cskaoyan.project1.model.vo.foreground.UserLoginAndRegisterVO;
import com.cskaoyan.project1.service.backstage.UserService;
import com.cskaoyan.project1.service.backstage.UserServiceImpl;
import com.cskaoyan.project1.utils.HttptUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//商城前端用户servlet
@WebServlet("/api/mall/user/*")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    private Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("did user doPost");
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/user/","");
        switch (action){
            case "signup":signup(request,response);
            case "login":login(request,response);
        }
    }
    //用户登陆
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String requsetBody = HttptUtils.getRequestBody(request);
        UserLoginBO loginBO = gson.fromJson(requsetBody,UserLoginBO.class);
        User user = userService.login(loginBO);
        if (user != null){
            request.getSession().setAttribute("user",user);
            response.getWriter().println(gson.toJson(Result.ok(new UserLoginAndRegisterVO(user))));
        }else {
            //response.getWriter().println(gson.toJson(Result.error("用户名或密码错误")));
            response.getWriter().println(Result.error("用户名或密码错误"));
        }
    }
    //用户注册
    private void signup(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String requsetBody = HttptUtils.getRequestBody(request);
        UserRegisterBO registerBO = gson.fromJson(requsetBody,UserRegisterBO.class);
        boolean res = userService.signup(registerBO);
        if (res){
            UserLoginAndRegisterVO vo = new UserLoginAndRegisterVO(registerBO.toUser());
            System.out.println(vo);
            response.getWriter().println(gson.toJson(Result.ok(vo)));
        }else {
            response.getWriter().println(gson.toJson(Result.error("email重复")));
        }
    }

    //*****************************************************************************************************************************
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("did user doGet");
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/user/","");
        switch (action){

        }

    }

}
