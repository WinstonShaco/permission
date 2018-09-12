package com.winston.model;

/**
 * @Author: 于新泽
 * @Date: Created in 14:49 2018/8/27.
 * @site :
 * 权限表
 */


public class SysAcl {

    //权限id
    private Integer id;

    //权限码
    private String code;

    //权限名称
    private String name;

    //权限所在的权限模块ID
    private Integer aclModuleId;

    //请求的Url 可以填写正则表达式
    private String url;

    //类型 1：菜单，2：按钮，3：其他
    private Integer type;

    //状态，1：正常，0：冻结
    private Integer status;

    //权限在当前模块下的顺序，由小到大
    private Integer seq;

    //备注
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAclModuleId() {
        return aclModuleId;
    }

    public void setAclModuleId(Integer aclModuleId) {
        this.aclModuleId = aclModuleId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
