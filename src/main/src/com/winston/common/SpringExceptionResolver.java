package com.winston.common;

import com.winston.exception.ParamException;
import com.winston.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 于新泽
 * @Date: Created in 19:11 2018/8/29.
 * @site :
 * 全局异常处理类
 */

@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver{
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        //根据 request 取出当前的 URL
        String url = request.getRequestURI().toString();
        ModelAndView mv;
        //定义一个默认异常处理
        String defaultMsg = "System error";

        // .json , .page
        // 当时json请求的时候，操作；否则都当页面请求
        if(url.endsWith(".json")){
            //如果当前异常是自己定义的异常的话
            if(ex instanceof PermissionException || ex instanceof ParamException){
                //使用的msg值，就是我们抛出msg
                JsonData result = JsonData.fail(ex.getMessage());
                mv = new ModelAndView("jsonView",result.toMap());
            } else { //如果不是自己定义的异常
                //未知异常
                log.error("unknown json exception, url:" + url, ex);
                //这里要使用默认的message
                JsonData result = JsonData.fail(defaultMsg);
                mv = new ModelAndView("jsonView",result.toMap());
            }
        } else if (url.endsWith(".page")){//当用页面进行请求的时候
            //未知异常
            log.error("unknown page exception, url:" + url, ex);
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("exception",result.toMap());
        } else { // 既不是 .json 有不是 .page 处理的情况
            //未知异常
            log.error("unknown exception, url:" + url, ex);
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("jsonView",result.toMap());
        }
        return mv;
    }
}
