package com.cskaoyan.project1.controller.backstage;

import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.User;
import com.cskaoyan.project1.service.backstage.UserService;
import com.cskaoyan.project1.service.backstage.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/user/*")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    private Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("did doPost");
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/user/","");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("did doGet");
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/user/","");
        switch (action)
        {
            case "allUser":allUser(request,response);break;
            case "deleteUser":deleteUser(request,response);break;
            case "searchUser":searchUser(request,response);break;
        }
    }
    //根据昵称模糊查询用户
    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nick = request.getParameter("word");
        List<User> userList = userService.searchUser(nick);
        response.getWriter().println(gson.toJson(Result.ok(userList)));
    }

    //根据id删除user
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int id = Integer.valueOf(request.getParameter("id"));
        userService.deleteUser(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    //返回所有user
    private void allUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        List<User> userList =  userService.allUser();
        response.getWriter().println(gson.toJson(Result.ok(userList)));
    }
}
