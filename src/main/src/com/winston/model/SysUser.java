package com.winston.model;

/**
 * @Author: 于新泽
 * @Date: Created in 18:21 2018/8/29.
 * @site :
 * 用户表
 */

public class SysUser {

    //用户ID
    private Integer id;

    //用户名称
    private String username;

    //手机号
    private String telephone;

    //邮箱
    private String mail;

    //密码
    private String password;

    //所在部门ID
    private Integer deptId;

    //状态，1：正常，0：冻结状态，2：删除
    private Integer status;

    //备注
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
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
