package com.winston.model;

/**
 * @Author: 于新泽
 * @Date: Created in 19:51 2018/8/27.
 * @site :
 * 权限相关log表
 */

public class SysLog {

    //id
    private Integer id;

    //权限更新的类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6：角色用户关系，7：角色权限关系
    private Integer type;

    //基于type后指定的对象id，比如用户、权限、角色等表的主键
    private Integer targetId;

    //当前是否复原过，0：没有，1：复原过
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
