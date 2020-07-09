package com.cskaoyan.project1.controller.backstage;

import com.alibaba.druid.util.StringUtils;
import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.bo.backstage.goods.*;
import com.cskaoyan.project1.model.bo.backstage.message.ReplyBO;
import com.cskaoyan.project1.model.vo.backstage.GoodsInfoEditVO;
import com.cskaoyan.project1.model.vo.backstage.MessageNoReplyVO;
import com.cskaoyan.project1.model.vo.backstage.MessageRepliedVO;
import com.cskaoyan.project1.model.vo.backstage.TypeGoodsVO;
import com.cskaoyan.project1.service.backstage.GoodsService;
import com.cskaoyan.project1.service.backstage.GoodsServiceImpl;
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
        } else if ("updateGoods".equals(action)) {
            updateGoods(request,response);
        } else if ("addSpec".equals(action)){
            addSpec(request,response);
        } else if ("deleteSpec".equals(action)) {
            deleteSpec(request,response);
        } else if ("reply".equals(action)) {
            reply(request,response);
        }
    }
    //回复消息
    private void reply(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttptUtils.getRequestBody(request);
        ReplyBO replyBO = gson.fromJson(requestBody,ReplyBO.class);
        goodsService.reply(replyBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    //更改商品信息的时候删除规格
    private void deleteSpec(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String requestBody = HttptUtils.getRequestBody(request);
        EditDeletSpecBO deletSpecBO = gson.fromJson(requestBody,EditDeletSpecBO.class);
        goodsService.deleteSpec(deletSpecBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }
    //!!!!无法解决当添加时用户输入全为空的数据问题（此时前端卡顿）
    //更改商品信息时候的添加规格
    private void addSpec(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String requestBody = HttptUtils.getRequestBody(request);
        EditAddSpecBO editAddSpecBO = gson.fromJson(requestBody, EditAddSpecBO.class);
        boolean res = goodsService.addSpec(editAddSpecBO);
        if (res)response.getWriter().println(gson.toJson(Result.ok()));
        else response.getWriter().println(gson.toJson(Result.error("插入失败，重复数据")));
    }

    //更改商品信息
    private void updateGoods(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String requestBody = HttptUtils.getRequestBody(request);
        GoodsEditComfirmBO comfirmBO = gson.fromJson(requestBody,GoodsEditComfirmBO.class);
        goodsService.updateGoods(comfirmBO);
        response.getWriter().println(gson.toJson(Result.ok()));
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
        } else if ("getGoodsInfo".equals(action)) {
            getGoodsInfo(request,response);
        } else if ("deleteGoods".equals(action)){
            deleteGoods(request,response);
        } else if ("noReplyMsg".equals(action)) {
            noReplyMsg(request,response);
        } else if ("repliedMsg".equals(action)) {
            repliedMsg(request,response);
        } else if ("deleteType".equals(action)) {
            deleteType(request,response);
        }
    }
    //删除类目，（订单删除待完成，删除商品（评论，订单），删除类目）
    private void deleteType(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Integer typeId = Integer.valueOf(request.getParameter("typeId"));
        goodsService.deleteType(typeId);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    //返回已留言列表
    private void repliedMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<MessageRepliedVO> messageRepliedVOList = goodsService.repliedMsg();
        response.getWriter().println(gson.toJson(Result.ok(messageRepliedVOList)));
    }

    //返回未留言列表
    private void noReplyMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<MessageNoReplyVO> messageNoReplyVOList = goodsService.noReplyMsg();
        response.getWriter().println(gson.toJson(Result.ok(messageNoReplyVOList)));
    }

    //删除某个商品
    private void deleteGoods(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int id = Integer.valueOf(request.getParameter("id"));
        goodsService.deleteGoods(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * 根据id获取商品信息
     * @param request
     * @param response
     */
    private void getGoodsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int id = StringUtils.stringToInteger(request.getParameter("id"));
        GoodsInfoEditVO goodsInfo = goodsService.getGoodsInfo(id);
        response.getWriter().println(gson.toJson(Result.ok(goodsInfo)));
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
