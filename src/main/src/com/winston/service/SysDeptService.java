package com.winston.service;

import com.google.common.base.Preconditions;
import com.winston.common.RequestHolder;
import com.winston.dao.SysDeptMapper;
import com.winston.exception.ParamException;
import com.winston.model.SysDept;
import com.winston.param.DeptParam;
import com.winston.util.BeanValidator;
import com.winston.util.IpUtil;
import com.winston.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: 于新泽
 * @Date: Created in 17:15 2018/9/24.
 * @site :
 */

@Service
public class SysDeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    /**
     * service 方法 基本验证，如果没有异常开始组装部门类。
     * @param param ：
     */
    public void save(DeptParam param){
        //参数校验
        BeanValidator.check(param);
        if (checkExist(param.getParentId(),param.getName(),param.getId())){
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept dept = SysDept.builder()
                .name(param.getName())
                .parentId(param.getParentId())
                .seq(param.getSeq())
                .remark(param.getRemark())
                .build();

        dept.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        dept.setOperator(RequestHolder.getCurrentUser().getUsername());
        dept.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequset()));
        //dept.setOperator(new Date());
        sysDeptMapper.insertSelective(dept);
    }



    /**
     * 校验部门重复，同一部门下不能出现重名的部门
     * @param parentId ：上级部门ID
     * @param deptName ：部门名字
     * @param deptId ：部门ID
     * @return ： 如果大于0 就说明当前值存在了
     */
    private boolean checkExist(Integer parentId, String deptName, Integer deptId){
        return sysDeptMapper.countByNameAndParentId(parentId, deptName, deptId) > 0;
    }

    private String getLevel(Integer deptId){
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        if(dept == null){
            return null;
        }
        return dept.getLevel();
    }

    public void update(DeptParam param) {

        //参数校验
        BeanValidator.check(param);
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }

        SysDept before = sysDeptMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的部门不存在！");

        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }

        SysDept after = SysDept.builder()
                .id(param.getId())
                .name(param.getName())
                .parentId(param.getParentId())
                .seq(param.getSeq())
                .remark(param.getRemark())
                .build();
        after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()),param.getParentId()));
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequset()));
        //after.setOperator(new Date());

        updateWithChild(before,after);
    }

    /**
     * 更新当前部门，以及该部门下的子部门。
     * 这里就要求用事务。
     * @param before : 更新之前的部门
     * @param after ： 更新之后的部门
     */
    @Transactional
    void updateWithChild(SysDept before, SysDept after){


        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        //判断一下是否需要更新子部门
        if(!after.getLevel().equals(before.getLevel())){
            //先取出当前部门的子部门
            List<SysDept> deptList = sysDeptMapper.getChildDeptListByLevel(before.getLevel());
            if(CollectionUtils.isNotEmpty(deptList)){
                for (SysDept dept : deptList) {
                    String level = dept.getLevel();
                    if(level.indexOf(oldLevelPrefix) == 0){
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        dept.setLevel(level);
                    }
                }
                sysDeptMapper.batchUpdateLevel(deptList);
            }
        }
        sysDeptMapper.updateByPrimaryKey(after);
    }
}
