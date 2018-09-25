package com.winston.dto;

import com.google.common.collect.Lists;
import com.winston.model.SysDept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Author: 于新泽
 * @Date: Created in 22:29 2018/9/25.
 * @site : 适配
 */

@Getter
@Setter
@ToString
public class DeptLevelDto extends SysDept{

    private List<DeptLevelDto> deptList = Lists.newArrayList();

    /**
     * 当传入 dept 的时候可以直接转换当前结构
     * @param dept
     * @return
     */
    public static DeptLevelDto adapt(SysDept dept){
        DeptLevelDto dto = new DeptLevelDto();
        BeanUtils.copyProperties(dept, dto);
        return dto;
    }
}
