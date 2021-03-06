package com.winston.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: 于新泽
 * @Date: Created in 20:11 2018/10/3.
 * @site :
 */
@Getter
@Setter
@ToString
public class UserParam {

    //用户ID
    private Integer id;

    //用户名称
    @NotBlank(message = "用户名不能为空")
    @Length(min = 1, max = 20, message = "用户名长度需要在20个字以内")
    private String username;

    //手机号
    @NotBlank(message = "电话不可以为空")
    @Length(min = 1, max = 13, message = "电话长度需要在13个字以内")
    private String telephone;

    //邮箱
    @NotBlank(message = "邮箱不允许为空")
    @Length(min = 5, max = 50, message = "邮箱长度需要在50个字符以内")
    private String mail;

    //所在部门ID
    @NotNull(message = "必须提供用户所在的部门")
    private Integer deptId;

    //状态，1：正常，0：冻结状态，2：删除
    @NotNull(message = "必须指定用户的状态")
    @Min(value = 0, message = "用户状态不合法")
    @Max(value = 2, message = "用户状态不合法")
    private Integer status;

    //备注
    @Length(min = 0, max = 200, message = "备注长度需要在200个字以内")
    private String remark = "";
}
