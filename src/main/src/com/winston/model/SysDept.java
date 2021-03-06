package com.winston.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: 于新泽
 * @Date: Created in 19:45 2018/8/27.
 * @site :
 * 部门表
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysDept {

    //部门ID
    private Integer id;

    //部门名称
    private String name;

    //上级部门ID
    private Integer parentId;

    //部门层级
    private String level;

    //部门在当前层级下的顺序
    private Integer seq;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
