package com.winston.util;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;

/**
 * @Author: 于新泽
 * @Date: Created in 23:50 2018/9/13.
 * @site : Json转换工具
 */

@Slf4j
public class JsonMapper {

    //核心转换器
    private static ObjectMapper objectMapper = new ObjectMapper();

    static{
        //config
        //排除掉为空的字段
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    }

    //这里要把对象转换为字符串
    public static <T> String obj2String(T src){
        if(src == null){
            return null;
        }
        try{
            return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
        }catch (Exception e){
            log.warn("parse Object to String exception, error:{}", e);
            return null;
        }
    }

    //将字符串转换为对象
    public static <T> T string2Obj(String src , TypeReference<T> typeReference){
        if(src == null || typeReference == null){
            return null;
        }
        try{
            return (T) (typeReference.getType().equals(String.class) ? src : objectMapper.readValue(src,typeReference));
        }catch (Exception e){
            log.warn("parse String to Object exception, String:{}, TypeReference<T>:{}, error:{}", src, typeReference.getType(), e);
            return null;
        }
    }
}
