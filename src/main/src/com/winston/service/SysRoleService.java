package com.winston.service;

import com.google.common.base.Preconditions;
import com.winston.common.RequestHolder;
import com.winston.dao.SysRoleMapper;
import com.winston.exception.ParamException;
import com.winston.model.SysRole;
import com.winston.param.RoleParam;
import com.winston.util.BeanValidator;
import com.winston.util.IpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 于新泽
 * @Date: Created in 7:50 2018/11/2.
 * @site :
 */

@Service
public class SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    /**
     * 新增角色
     * @param param
     */
    public void save(RoleParam param){
        BeanValidator.check(param);
        if(checkExist(param.getName(),param.getId()){
            throw new ParamException("角色名称已经存在");
        }
        SysRole role = SysRole.builder()
                .name(param.getName())
                .status(param.getStatus())
                .type(param.getType())
                .remark(param.getRemark())
                .build();
        role.setOperator(RequestHolder.getCurrentUser().getUsername());
        role.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequset()));
        sysRoleMapper.insertSelective(role);

    }

    /**
     * 更新角色
     * @param param
     */
    public void update(RoleParam param){
        BeanValidator.check(param);
        if(checkExist(param.getName(),param.getId())){
            throw new ParamException("角色名称已经存在");
        }
        SysRole before = sysRoleMapper.selectByPrimaryKey(param.getId());

        Preconditions.checkNotNull(before,"待更新的角色不存在");
        SysRole after = SysRole.builder()
                .id(param.getId())
                .name(param.getName())
                .status(param.getStatus())
                .type(param.getType())
                .remark(param.getRemark())
                .build();
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequset()));
        sysRoleMapper.updateByPrimaryKeySelective(after);
    }

    /**
     * 校验角色名称不能重复
     * @param name
     * @param id
     * @return
     */
    private boolean checkExist(String name, Integer id){

        return sysRoleMapper.countByName(name, id) > 0;
    }

    public List<SysRole> getAll() {
        return sysRoleMapper.getAll();
    }
}
