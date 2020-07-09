package com.cskaoyan.project1.controller.backstage;

import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.bo.backstage.orders.ChangeOrderBO;
import com.cskaoyan.project1.model.vo.backstage.orders.OrdersEditVO;
import com.cskaoyan.project1.model.bo.backstage.orders.OrdersSearchBO;
import com.cskaoyan.project1.model.vo.backstage.PageOrdersVO;
import com.cskaoyan.project1.service.backstage.OrdersService;
import com.cskaoyan.project1.service.backstage.OrdersServiceImpl;
import com.cskaoyan.project1.utils.HttptUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/admin/order/*")
public class OrdersServlet extends HttpServlet {
    Gson gson = new Gson();
    OrdersService ordersService = new OrdersServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/order/","");
        switch (action) {
            case "ordersByPage":ordersByPage(request,response);break;
            case "changeOrder":changeOrder(request,response);break;
        }
    }
    //改变订单状态(功能实现：改变规格，数量，订单状态）
    private void changeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String requestBody = HttptUtils.getRequestBody(request);
        ChangeOrderBO changeOrderBO = gson.fromJson(requestBody,ChangeOrderBO.class);
        ordersService.changeOrder(changeOrderBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * 根据条件查询订单
     * @param request
     * @param response
     */
    private void ordersByPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String requestBody = HttptUtils.getRequestBody(request);
        OrdersSearchBO searchBO = gson.fromJson(requestBody,OrdersSearchBO.class);
        PageOrdersVO pageOrdersVO = ordersService.ordersByPage(searchBO);
        response.getWriter().println(gson.toJson(Result.ok(pageOrdersVO)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/order/","");
        switch (action) {
            case "order":order(request,response);break;
            case "deleteOrder":deleteOrder(request,response);break;
        }
    }
    //根据参数：id删除订单
    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int id = Integer.valueOf(request.getParameter("id"));
        ordersService.deleteOrder(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    //编辑页面，预先查询订单
    private void order(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int orderId = Integer.valueOf(request.getParameter("id"));
        OrdersEditVO ordersEditVO = ordersService.order(orderId);
        response.getWriter().println(gson.toJson(Result.ok(ordersEditVO)));
    }
}
