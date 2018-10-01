package com.winston.dao;

import com.winston.model.SysDept;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface SysDeptMapper {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    // 获取当前部门的列表
    List<SysDept> getAllDept();

    //取出当前部门的子部门
    List<SysDept> getChildDeptListByLevel(@Param("level") String level);

    //批量更新level
    void batchUpdateLevel(@Param("sysDeptList") List<SysDept> sysDeptList);

    //校验部门名称是否重复
    int countByNameAndParentId(@Param("parentId") Integer parentId, @Param("name") String name, @Param("id") Integer id);
}