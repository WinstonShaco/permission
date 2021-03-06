package com.winston.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * @Author: 于新泽
 * @Date: Created in 18:06 2018/8/29.
 * @site :
 * 角色表
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRole {

    //角色ID
    private Integer id;

    //角色名称
    private String name;

    //角色的类型 1：管理员角色，2：其他
    private Integer type;

    //状态 1：可用，0：冻结
    private Integer status;

    //备注
    private String remark;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
