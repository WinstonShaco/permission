package com.winston.model;

/**
 * @Author: 于新泽
 * @Date: Created in 23:25 2018/8/27.
 * @site :
 */

public class SysLogWithBLOBs extends SysLog {

    //旧值
    private String oldValue;

    //新值
    private String newValue;

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}
