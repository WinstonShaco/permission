package com.winston.dto;

import com.google.common.collect.Lists;
import com.winston.model.SysAclModule;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Author: 于新泽
 * @Date: Created in 23:08 2018/10/22.
 * @site :
 */

@Getter
@Setter
@ToString
public class AclModuleLevelDto extends SysAclModule{

    // List下包含自己 可以展示出树形结构
    private List<AclModuleLevelDto> aclModuleList = Lists.newArrayList();

    /**
     * 当传入 sysDept 的时候可以直接转换当前结构
     * @param aclModule
     * @return
     */
    public static AclModuleLevelDto adapt(SysAclModule aclModule){
        AclModuleLevelDto dto = new AclModuleLevelDto();
        //copy bean的一个工具
        BeanUtils.copyProperties(aclModule, dto);
        return dto;
    }
}
