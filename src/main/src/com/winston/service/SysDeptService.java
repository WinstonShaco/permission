package com.winston.service;

import com.winston.dao.SysDeptMapper;
import com.winston.exception.ParamException;
import com.winston.model.SysDept;
import com.winston.param.DeptParam;
import com.winston.util.BeanValidator;
import com.winston.util.LevelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
        //dept.setOpera
        sysDeptMapper.insertSelective(dept);
    }

    /**
     * 校验部门重复，同一部门下不能出现重名的部门
     * @param parentId ：上级部门ID
     * @param deptName ：部门名字
     * @param deptId ：部门ID
     * @return
     */
    private boolean checkExist(Integer parentId, String deptName, Integer deptId){

        //TODO:
        return true;
    }

    private String getLevel(Integer deptId){
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        if(dept == null){
            return null;
        }
        return dept.getLevel();
    }
}
