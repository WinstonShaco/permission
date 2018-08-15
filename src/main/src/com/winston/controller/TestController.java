package com.winston.controller;

import lombok.extern.slf4j.Slf4j;
import oracle.jrockit.jfr.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 于新泽
 * @Date: Created in 0:20 2018/8/15.
 * @site :
 */

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        //log.info("hello");
        return "Hello ,permission";
    }
}
