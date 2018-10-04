package com.winston.controller;

import com.winston.model.SysUser;
import com.winston.service.SysUserService;
import com.winston.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 于新泽
 * @Date: Created in 11:46 2018/10/4.
 * @site :
 */
@Controller
public class UserController {

    @Resource
    private SysUserService sysUserService;

    // 用户退出
    @RequestMapping("logout.page")
    public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        request.getSession().invalidate();
        String path = "signin.jsp";
        response.sendRedirect(path);
    }

    // 用户登录
    @RequestMapping("login.page")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        SysUser sysUser = sysUserService.findByKeyword(username);
        String errorMsg = "";
        // 记录跳转到登录界面之前的url
        String ret = request.getParameter("ret");

        if(StringUtils.isBlank(username)){
            errorMsg = "用户名不可以为空";
        } else if (StringUtils.isBlank(password)){
            errorMsg = "密码不可以为空";
        } else if (sysUser == null) {
            errorMsg = "查询不到指定用户";
        } else if (!sysUser.getPassword().equals(MD5Util.encrypt(password))){
            errorMsg = "用户名或密码错误";
        } else if (sysUser.getStatus() != 1){ // 账号不是登录状态
            errorMsg = "用户已被冻结，请联系管理员";
        } else {
            // login
            request.getSession().setAttribute("user",sysUser);
            if(StringUtils.isNotBlank(ret)) {
                response.sendRedirect(ret);
            } else {
                response.sendRedirect("/admin/index.page"); // TODO:
            }
        }

        request.setAttribute("error", errorMsg);
        request.setAttribute("username", username);
        if (StringUtils.isNoneBlank(ret)){
            request.setAttribute("ret",ret);
        }
        // 登录页面
        String path = "signin.jsp";
        // 页面跳转
        request.getRequestDispatcher(path).forward(request,response);
    }
}
