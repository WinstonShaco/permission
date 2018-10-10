package com.winston.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * @Author: 于新泽
 * @Date: Created in 19:37 2018/8/27.
 * @site :
 * 权限模块表
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysAclModule {

    //权限模块ID
    private Integer id;

    //权限模块名称
    private String name;

    //上级权限模块id
    private Integer parentId;

    //权限模块层级
    private String level;

    //权限模块下在当前层级下的顺序
    private Integer seq;

    //状态 1：正常， 0：冻结
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
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
