package com.winston.param;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: 于新泽
 * @Date: Created in 8:18 2018/10/9.
 * @site :
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AclModelParam {

    //权限模块ID
    private Integer id;

    //权限模块名称
    @NotBlank(message = "权限模块名称不可以为空")
    @Length(min = 2, max = 20, message = "权限模块名称长度要在2到20个字符之间")
    private String name;

    //上级权限模块id
    private Integer parentId = 0;

    //权限模块层级
    //private String level;

    //权限模块下在当前层级下的顺序
    @NotNull(message = "权限模板展示顺序不可以为空")
    private Integer seq;

    //状态 1：正常， 0：冻结
    @NotNull(message = "权限模板状态不可以为空")
    @Min(value = 0, message = "权限模板状态不合法")
    @Max(value = 1, message = "权限模板状态不合法")
    private Integer status;

    //备注
    @Length(max = 200, message = "权限模块备注长度要在200个字符之内")
    private String remark;
}
