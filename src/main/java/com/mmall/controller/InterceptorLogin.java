package com.mmall.controller;

import com.mmall.common.Const;
import com.mmall.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class InterceptorLogin extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("-----拦截请求-----");
        HttpSession session = request.getSession();
        //return true;
        User find = (User) session.getAttribute(Const.CURRENT_USER);
        if(find != null){
            return true;
        }else{
            //PrintWriter printWriter = httpServletResponse.getWriter();
            //printWriter.write("{code:0,message:\"session is invalid,please login again!\"}");
            //跳转登录
            String url = "/html/login.html";
            response.sendRedirect(url);
            return false;
        }
    }
}
