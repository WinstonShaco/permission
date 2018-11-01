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
 * @Date: Created in 10:55 2018/10/30.
 * @site :
 */

@Getter
@Setter
@ToString
public class AclParam {

    //权限id
    private Integer id;

    //权限码
//    private String code;

    //权限名称
    @NotBlank(message = "权限名称不可以为空")
    @Length(min = 2, max = 20, message = "权限点名称长度需要在2-20个字之间")
    private String name;

    //权限所在的权限模块ID
    @NotNull(message = "必须指定权限模块")
    private Integer aclModuleId;

    //请求的Url 可以填写正则表达式
    @Length(min = 6, max = 100, message = "权限点URL长度需要在6-100个字符之间")
    private String url;

    //类型 1：菜单，2：按钮，3：其他
    @NotNull(message = "必须指定权限点的类型")
    @Min(value = 1, message = "权限点类型不合法")
    @Max(value = 3, message = "权限点类型不合法")
    private Integer type;

    //状态，1：正常，0：冻结
    @NotNull(message = "必须指定权限点的类型")
    @Min(value = 0, message = "权限点状态不合法")
    @Max(value = 1, message = "权限点状态不合法")
    private Integer status;

    //权限在当前模块下的顺序，由小到大
    @NotNull(message = "必须指定权限点的展示顺序")
    private Integer seq;

    //备注
    @Length(min = 0, max = 200, message = "权限点备注长度需要200个字符以内")
    private String remark;


}
