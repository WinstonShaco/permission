package com.winston.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: 于新泽
 * @Date: Created in 23:02 2018/9/12.
 * @site : 测试 BeanVaildator 是否正确
 * 本文件是基于注解的
 */

@Getter
@Setter
public class TestVo {

    @NotBlank
    //@NotBlank不允许为空值,String
    private String msg;

    @NotNull(message = "id 不能为空")
    @Max(value = 10,message = "id 不能大于10")
    @Min(value = 0,message = "id 至少大于等于0")
    //不允许为空
    private Integer id;

    //@NotEmpty
    //数组或者list校验不为空
    private List<String> str;
}
