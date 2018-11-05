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
 * @Date: Created in 7:45 2018/11/2.
 * @site :
 */

@Getter
@Setter
@ToString
public class RoleParam {

    //角色ID
    private Integer id;

    //角色名称
    @NotBlank(message = "角色名称不可以为空")
    @Length(min = 2, max = 20, message = "角色名称长度需要在2-20个字之间")
    private String name;

    //角色的类型 1：管理员角色，2：其他
    @Min(value = 1, message = "角色类型不合法")
    @Max(value = 2, message = "角色类型不合法")
    private Integer type;

    //状态 1：可用，0：冻结
    @NotNull(message = "角色状态不可以为空")
    @Min(value = 0, message = "角色状态不合法")
    @Max(value = 1, message = "角色状态不合法")
    private Integer status;

    //备注
    @Length(min = 0, max = 200, message = "角色备注长度需要在200个字符以内")
    private String remark;
}
