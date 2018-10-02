package com.winston.model;

/**
 * @Author: 于新泽
 * @Date: Created in 18:17 2018/8/29.
 * @site :
 * 角色用户权限表
 */

public class SysRoleUser {

    //ID
    private Integer id;

    //角色ID
    private Integer roleId;

    //用户id
    private Integer userId;

    private String operator;

    private String operateIp;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
