package com.cskaoyan.project1.controller.foreground;

import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.bo.backstage.Admin.AdminAddBO;
import com.cskaoyan.project1.model.bo.backstage.Admin.AdminLoginBO;
import com.cskaoyan.project1.model.bo.backstage.Admin.AdminSearchBO;
import com.cskaoyan.project1.model.bo.backstage.Admin.ChangePwdBo;
import com.cskaoyan.project1.model.vo.backstage.AdminLoginVO;
import com.cskaoyan.project1.service.backstage.AdminService;
import com.cskaoyan.project1.service.backstage.AdminServiceImpl;
import com.cskaoyan.project1.service.backstage.GoodsService;
import com.cskaoyan.project1.service.backstage.GoodsServiceImpl;
import com.cskaoyan.project1.utils.HttptUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//商城前端首页servlet
@WebServlet("/api/mall/index/*")
public class MallServlet extends HttpServlet {

    private GoodsService goodsService = new GoodsServiceImpl();
    private Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("did mall doPost");
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/index/","");

    }

    //*****************************************************************************************************************************
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("did mall doGet");
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/index/","");
        switch (action){
            case "getType":getType(request,response);break;
        }

    }

    private void getType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Type> typeList = goodsService.getType();
        response.getWriter().println(gson.toJson(Result.ok(typeList)));
    }
}
