package com.winston.service;

import com.google.common.base.Preconditions;
import com.winston.common.RequestHolder;
import com.winston.dao.SysAclModuleMapper;
import com.winston.exception.ParamException;
import com.winston.model.SysAclModule;
import com.winston.model.SysDept;
import com.winston.param.AclModelParam;
import com.winston.util.BeanValidator;
import com.winston.util.IpUtil;
import com.winston.util.LevelUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 于新泽
 * @Date: Created in 23:03 2018/10/10.
 * @site :
 */

@Service
public class SysAclModuleService {

    @Resource
    private SysAclModuleMapper sysAclModuleMapper;

    public void save(AclModelParam param){
        BeanValidator.check(param);
        if(checkExist(param.getParentId(), param.getName(), param.getId())){
            throw new ParamException("同一层级下存在相同名称的权限模块");
        }
        SysAclModule aclModule = SysAclModule
                .builder()
                .name(param.getName())
                .parentId(param.getParentId())
                .seq(param.getSeq())
                .status(param.getStatus())
                .remark(param.getRemark())
                .build();
        aclModule.setOperator(RequestHolder.getCurrentUser().getUsername());
        aclModule.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequset()));
        // aclModule.setOperateTime(new Date());
        sysAclModuleMapper.insertSelective(aclModule);
    }

    public void update(AclModelParam param){
        BeanValidator.check(param);
        if(checkExist(param.getParentId(), param.getName(), param.getId())){
            throw new ParamException("同一层级下存在相同名称的权限模块");
        }
        SysAclModule before = sysAclModuleMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before,"待更新的权限不存在");
        SysAclModule after = SysAclModule
                .builder()
                .name(param.getName())
                .parentId(param.getParentId())
                .seq(param.getSeq())
                .status(param.getStatus())
                .remark(param.getRemark())
                .build();
        after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequset()));
        // after.setOperateTime(new Date);

        updateWithChild(before,after);
    }

    @Transactional
    void updateWithChild(SysAclModule before, SysAclModule after){
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if(!after.getLevel().equals(before.getLevel())){
            List<SysAclModule> aclModuleList = sysAclModuleMapper.getChildAclModuleListByLevel(before.getLevel());
            for(SysAclModule aclModule: aclModuleList){
                String level = aclModule.getLevel();
                if(level.indexOf(oldLevelPrefix) == 0){
                    level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                    aclModule.setLevel(level);
                }
            }
            sysAclModuleMapper.batchUpdateLevel(aclModuleList);
        }
        sysAclModuleMapper.updateByPrimaryKeySelective(after);

    }

    private boolean checkExist(Integer parentId, String aclModuleName, Integer deptId){
        return sysAclModuleMapper.countByNameAndParentId(parentId, aclModuleName, deptId) > 0;
    }

    private String getLevel(Integer aclModuleId){
        SysAclModule aclModule = sysAclModuleMapper.selectByPrimaryKey(aclModuleId);
        if(aclModule == null){
            return null;
        }
        return aclModule.getLevel();
    }
}
