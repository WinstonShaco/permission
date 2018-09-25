package com.winston.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.winston.dao.SysDeptMapper;
import com.winston.dto.DeptLevelDto;
import com.winston.model.SysDept;
import com.winston.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: 于新泽
 * @Date: Created in 22:44 2018/9/25.
 * @site :
 */

@Service
public class SysTreeService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    /**
     * 返回部门树
     * @return
     */
    public List<DeptLevelDto> deptTree(){
        List<SysDept> deptList = sysDeptMapper.getAllDept();

        List<DeptLevelDto> dtoList = Lists.newArrayList();
        for(SysDept dept : deptList){
            DeptLevelDto dto = DeptLevelDto.adapt(dept);
            dtoList.add(dto);
        }
        return deptListToTree(dtoList);
    }

    /**
     * 将部门列表转换成树形结构
     * 这里不停地递归来把树组建出来
     * @param deptLevelList
     * @return
     */
    public List<DeptLevelDto> deptListToTree (List<DeptLevelDto> deptLevelList){
        if (CollectionUtils.isEmpty(deptLevelList)) {
            return Lists.newArrayList();
        }
        // level -> [dept1, dept2, ...]
        Multimap<String, DeptLevelDto> levelDeptMap = ArrayListMultimap.create();
        List<DeptLevelDto> rootList = Lists.newArrayList();

        for(DeptLevelDto dto : deptLevelList) {
            levelDeptMap.put(dto.getLevel(), dto);
            if(LevelUtil.ROOT.equals(dto.getLevel())){
                rootList.add(dto);
            }
        }
        //按照 seq 从小到大排序
        Collections.sort(rootList, new Comparator<DeptLevelDto>() {
            @Override
            public int compare(DeptLevelDto o1, DeptLevelDto o2) {
                return o1.getSeq() - o2.getSeq();
            }
        });
        //递归生成树
        transformDeptTree(rootList, LevelUtil.ROOT, levelDeptMap);
        return rootList;
    }

    /**
     *
     * @param deptLevelList : 当前层级的部门列表
     * @param level ：每次都会更新，通过level会知道当前层级有哪些部门
     * @param levelDeptMap ：为了辅助计算
     */
    public void transformDeptTree(List<DeptLevelDto> deptLevelList,String level,Multimap<String, DeptLevelDto> levelDeptMap) {

        for (int i = 0; i < deptLevelList.size() ; i++){
            // 遍历该层的每个元素
            DeptLevelDto deptLevelDto = deptLevelList.get(i);
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDto.getId());
            // 处理下一层
            List<DeptLevelDto> tempDeptList = (List<DeptLevelDto>) levelDeptMap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(tempDeptList)){
                //排序
                Collections.sort(tempDeptList, deptSeqComparator);
                //设置下一层部门
                deptLevelDto.setDeptList(tempDeptList);
                //进入到下一层
                transformDeptTree(tempDeptList, nextLevel, levelDeptMap);
            }
        }
    }

    public Comparator<DeptLevelDto> deptSeqComparator = new Comparator<DeptLevelDto>() {
        @Override
        public int compare(DeptLevelDto o1, DeptLevelDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    }
}
