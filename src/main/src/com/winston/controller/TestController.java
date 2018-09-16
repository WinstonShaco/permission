package com.winston.controller;

import com.winston.common.ApplicationContextHelper;
import com.winston.common.JsonData;
import com.winston.dao.SysAclModuleMapper;
import com.winston.exception.ParamException;
import com.winston.exception.PermissionException;
import com.winston.model.SysAclModule;
import com.winston.param.TestVo;
import com.winston.util.BeanValidator;
import com.winston.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: 于新泽
 * @Date: Created in 0:20 2018/8/15.
 * @site :
 */

@Controller
@RequestMapping("/test")
@Slf4j
@ControllerAdvice
public class TestController {

    /**
     * 定义异常检验
     * @return
     */
    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello(){
        log.info("hello");
        throw new PermissionException("test exception");
        //return JsonData.success("Hello ,permission");
    }

    /**
     * 校验Validate
     * @return
     */
    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(TestVo vo) throws ParamException{
        log.info("validate");
        SysAclModuleMapper moduleMapper = ApplicationContextHelper.popBean(SysAclModuleMapper.class);
        SysAclModule module = moduleMapper.selectByPrimaryKey(1);
        log.info(JsonMapper.obj2String(module));
        BeanValidator.check(vo);
//        try {
//            //这里通过validateObject来校验
//            Map<String, String> map = BeanValidator.validateObject(vo);
//            if(MapUtils.isNotEmpty(map)){//如果能拿到值的话
//                for(Map.Entry<String, String> entry : map.entrySet()){//那么就遍历一下
//                    log.info(" {} -> {} " , entry.getKey() , entry.getValue());
//                }
//            }
//
//        } catch (Exception e){
//
//        }
        return JsonData.success("test validate");
    }
}
