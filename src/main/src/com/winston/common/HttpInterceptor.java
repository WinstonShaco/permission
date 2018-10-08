package com.winston.common;

import com.winston.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: 于新泽
 * @Date: Created in 0:20 2018/9/19.
 * @site :
 */

@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter{

    private static final String START_TIME = "requestStartTime";

    /**
     * 放在处理之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //拿到当前请求
        String url = request.getRequestURI().toString();
        //取当前请求的参数
        Map parameterMap = request.getParameterMap();
        log.info("request start. url:{},params:{}",url , JsonMapper.obj2String(parameterMap));
        long start = System.currentTimeMillis();
        request.setAttribute(START_TIME, start);
        return true;
    }

    /**
     * 在请求处理完之后会调用这个方法，在正常的情况下会调用
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //拿到当前请求
        String url = request.getRequestURI().toString();
        //取当前请求的参数
        //Map parameterMap = request.getParameterMap();
        long start = (long)request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();
        log.info("request finished. url:{},cost:{}",url , end - start);
        RequestHolder.remove();
    }

    /**
     * 在请求处理完之后会调用这个方法,在正常和异常的情况下都会调用。
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //拿到当前请求
//        String url = request.getRequestURI().toString();
        //取当前请求的参数
        //Map parameterMap = request.getParameterMap();
//        long start = (long)request.getAttribute(START_TIME);
//        long end = System.currentTimeMillis();
//        log.info("request compele. url:{},cost:{}",url , end - start);
        removeThreadLocalInfo();
    }

    public void removeThreadLocalInfo(){
        RequestHolder.remove();
    }
}
