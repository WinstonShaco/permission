package com.winston.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: 于新泽
 * @Date: Created in 13:54 2018/10/4.
 * @site :
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("index.page")
    public ModelAndView index (){
        return new ModelAndView("admin");
    }
}
