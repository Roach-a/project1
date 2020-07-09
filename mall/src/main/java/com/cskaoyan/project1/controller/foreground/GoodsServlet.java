package com.cskaoyan.project1.controller.foreground;

import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.vo.backstage.TypeGoodsVO;
import com.cskaoyan.project1.service.backstage.GoodsService;
import com.cskaoyan.project1.service.backstage.GoodsServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//商城前端首页servlet
@WebServlet("/api/mall/goods/*")
public class GoodsServlet extends HttpServlet {

    private GoodsService goodsService = new GoodsServiceImpl();
    private Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("did goods doPost");
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/goods/","");

    }

    //*****************************************************************************************************************************
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("did goods doGet");
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/goods/","");
        switch (action){
            case "getGoodsByType":getGoodsByType(request,response);break;
        }

    }

    private void getGoodsByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<TypeGoodsVO> goodsByType = goodsService.getGoodsByType(request.getParameter("typeId"));
        response.getWriter().println(gson.toJson(Result.ok(goodsByType)));
    }

    private void getType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Type> typeList = goodsService.getType();
        response.getWriter().println(gson.toJson(Result.ok(typeList)));
    }
}
