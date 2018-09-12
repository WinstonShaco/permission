package com.winston.common;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 于新泽
 * @Date: Created in 0:09 2018/8/26.
 * @site :
 */
@Getter
@Setter
public class JsonData {

    //返回结果
    private boolean ret;

    //异常处理返回的结果
    private String msg;

    //正常返回的时候给前台的数据
    private Object data;


    //之传入一个结果
    public JsonData(boolean ret){
        this.ret = ret;
    }

    //当成功的时候
    public static JsonData success(Object object, String msg){
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        jsonData.msg = msg;
        return jsonData;
    }

    //在实际情况中，有时成功不需要 msg
    public static JsonData success(Object object){
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        return jsonData;
    }

    //在实际情况中，有时成功不需要 msg
    public static JsonData success(){
        return new JsonData(true);
    }

    //失败时候的方法
    public static JsonData fail(String msg){
        JsonData jsonData = new JsonData(false);
        jsonData.msg = msg;
        return jsonData;
    }

    //成功时返回与之前一样的返回结果
    public Map<String,Object> toMap(){
        HashMap<String,Object> result = new HashMap<String, Object>();
        //放入相关的值
        result.put("ret",ret);
        result.put("msg",msg);
        result.put("data",data);
        return result;
    }
}
