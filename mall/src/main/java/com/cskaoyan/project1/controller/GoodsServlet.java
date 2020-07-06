package com.cskaoyan.project1.controller;

import com.alibaba.druid.util.StringUtils;
import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.bo.GoodsAddBO;
import com.cskaoyan.project1.model.bo.TypeBO;
import com.cskaoyan.project1.model.vo.TypeGoodsVO;
import com.cskaoyan.project1.service.GoodsService;
import com.cskaoyan.project1.service.GoodsServiceImpl;
import com.cskaoyan.project1.utils.FileUploadUtils;
import com.cskaoyan.project1.utils.HttptUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/api/admin/goods/*")
public class GoodsServlet extends HttpServlet {

    private GoodsService goodsService = new GoodsServiceImpl();
    private Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("did doPost");
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/","");
        if ("imgUpload".equals(action)) {
            imgUpload(request,response);
        }else if ("addGoods".equals(action)) {
            addGoods(request,response);
        }else if ("addType".equals(action)) {
            addType(request,response);
        }
    }

    /**
     * 添加类目
     * @param request
     * @param response
     */
    private void addType(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String requestBody = HttptUtils.getRequestBody(request);
        TypeBO typeBO = gson.fromJson(requestBody,TypeBO.class);
        boolean res = goodsService.addGoodsType(typeBO);
        if (res)response.getWriter().println(gson.toJson(Result.ok()));
        else response.getWriter().println(gson.toJson(Result.error("添加分类失败，已存在")));
        response.setHeader("refresh","0");
    }

    /**
     * 添加商品
     * @param request
     * @param response
     */
    private void addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String requestBody = HttptUtils.getRequestBody(request);
        GoodsAddBO goodsAddBO = gson.fromJson(requestBody,GoodsAddBO.class);
        goodsService.addGoods(goodsAddBO);
        response.getWriter().println(gson.toJson(Result.ok("添加商品成功")));
    }

    /**
     * 上传文件（在管理员页面，上传商品图片）
     * @param request
     * @param response
     */
    private void imgUpload(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String, Object> map = FileUploadUtils.parseRequest(request);
        String file = (String)map.get("file");
        String basePath = (String)getServletContext().getAttribute("domain");
        response.getWriter().println(gson.toJson(Result.ok(basePath + file)));
    }
//******************************************************************************************************************
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("did doGet");
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/","");
        if ("getType".equals(action)) {
            getType(request,response);
        } else if ("getGoodsByType".equals(action)) {
            getGoodsBuType(request,response);
        }
    }

    /**
     * 根据typeId查询该类下的所有商品
     * @param request
     * @param response
     */
    private void getGoodsBuType(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String typeId = request.getParameter("typeId");
        //校验
        if (!StringUtils.isNumber(typeId)){
            response.getWriter().println("类目id非法");
            return;
        }
        List<TypeGoodsVO> typeGoodsVOList = goodsService.getGoodsByType(typeId);
        response.getWriter().println(gson.toJson(Result.ok(typeGoodsVOList)));

    }

    /**
     * 返回所有商品分类
     * @param request
     * @param response
     */
    private void getType(HttpServletRequest request, HttpServletResponse response) throws IOException{
        List<Type> typeList = goodsService.getType();
        response.getWriter().println(gson.toJson(Result.ok(typeList)));
    }
}
