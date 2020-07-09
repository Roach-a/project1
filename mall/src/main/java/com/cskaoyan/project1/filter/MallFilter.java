package com.cskaoyan.project1.filter;

import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.Result;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/api/mall/*")
public class MallFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("did MallFilter");
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        //解决跨域问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //将*改为页面系统所在的域
        response.setHeader("Access-Control-Allow-Origin","http://localhost:8085");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,Authorization,Content-Type");
        response.setHeader("Access-Control-Allow-Credentials","true");
        System.out.println(request.getRequestURI());

        Admin admin = (Admin)request.getSession().getAttribute("admin");
        //1.需要拦截哪些，需要拦截的，判断有无session数据
//        if (auth(request)){//需要拦截
//            if (admin == null){
//                response.getWriter().println(new Gson().toJson(Result.error("错误，无权访问此页面")));
//                //response.getWriter().println(Result.error("错误，无权访问此页面"));
//                return;
//            }
//        }

        chain.doFilter(request, response);


    }

    private boolean auth(HttpServletRequest request) {
        if (request.getMethod().equals("OPTIONS")) return false;

            if (request.getRequestURI().equals("/api/admin/admin/login") || request.getRequestURI().equals("/api/admin/admin/logoutAdmin"))
            {
                return false;
            }

            return true;

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
