package com.winston.model;

/**
 * @Author: 于新泽
 * @Date: Created in 18:12 2018/8/29.
 * @site :
 * 角色权限关联表
 */

public class SysRoleAcl {

    //ID
    private Integer id;

    //角色ID
    private Integer roleId;

    //权限ID
    private Integer calId;

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

    public Integer getCalId() {
        return calId;
    }

    public void setCalId(Integer calId) {
        this.calId = calId;
    }
}
