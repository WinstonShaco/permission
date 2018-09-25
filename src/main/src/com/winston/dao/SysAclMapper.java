package com.winston.dao;

import com.winston.model.SysAcl;

/**
 * @Author: 于新泽
 * @Date: Created in 22:42 2018/9/17.
 * @site : 权限表
 */

public interface SysAclMapper {

    int deleteByPrimaryKey(Integer id);

    SysAcl selectByPrimaryKey(Integer id) throws Exception;
}
