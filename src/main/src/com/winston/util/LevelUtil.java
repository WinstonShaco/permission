package com.winston.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: 于新泽
 * @Date: Created in 17:36 2018/9/24.
 * @site :
 */

public class LevelUtil {

    public final static String SEPARATOR = ".";

    public final static String ROOT = "0";


    /**
     * 部门level计算规则
     * @param parentLevel
     * @param parentId
     * @return
     */
    public static String calculateLevel(String parentLevel, int parentId){
        //如果判断为空的话就认为是首层
        if(StringUtils.isBlank(parentLevel)){
            return ROOT;
        } else { // 用上一层 + . + 本层ID 拼成level eg: 0.1.2
            return StringUtils.join(parentLevel,SEPARATOR,parentId);
        }
    }
}
