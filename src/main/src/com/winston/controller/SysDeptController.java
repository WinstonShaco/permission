package com.winston.controller;

import com.winston.common.JsonData;
import com.winston.param.DeptParam;
import com.winston.service.SysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author: 于新泽
 * @Date: Created in 11:54 2018/9/23.
 * @site :
 */

@Controller
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController {

    @Resource
    private SysDeptService sysDeptService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveDept(DeptParam param){
        sysDeptService.save(param);
        return JsonData.success();
    }


}
