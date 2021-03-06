package com.winston.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.winston.dao.SysAclModuleMapper;
import com.winston.dao.SysDeptMapper;
import com.winston.dto.AclModuleLevelDto;
import com.winston.dto.DeptLevelDto;
import com.winston.model.SysAclModule;
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
 * @site : 计算树的结构
 */

@Service
public class SysTreeService {

    @Resource
    private SysDeptMapper sysDeptMapper;
    @Resource
    private SysAclModuleMapper sysAclModuleMapper;

    public List<AclModuleLevelDto> aclModuleTree(){
        List<SysAclModule> aclModuleList = sysAclModuleMapper.getAllAclModule();
        List<AclModuleLevelDto> dtoList = Lists.newArrayList();
        for(SysAclModule aclModule : aclModuleList){
            dtoList.add(AclModuleLevelDto.adapt(aclModule));
        }
        return aclModuleListToTree(dtoList);
    }

    public List<AclModuleLevelDto> aclModuleListToTree(List<AclModuleLevelDto> dtoList){
        if(CollectionUtils.isEmpty(dtoList)){
            return Lists.newArrayList();
        }
        // level -> [aclModule1, aclModule2, ...] 以 level 为 key ，后面存储的是dept的列表
        // List 的每一个结构都是 AclModuleLevelDto
        Multimap<String, AclModuleLevelDto> levelAclModuleMap = ArrayListMultimap.create();
        // 一级权限
        List<AclModuleLevelDto> rootList = Lists.newArrayList();

        for(AclModuleLevelDto dto : dtoList) {
            levelAclModuleMap.put(dto.getLevel(), dto);
            if(LevelUtil.ROOT.equals(dto.getLevel())){
                rootList.add(dto);
            }
        }
        Collections.sort(rootList, aclModuleSeqComparator);
        transformAclModuleTree(rootList, LevelUtil.ROOT, levelAclModuleMap);
        return rootList;
    }

    public void transformAclModuleTree(
            List<AclModuleLevelDto> dtoList,
            String level,
            Multimap<String, AclModuleLevelDto> levelAclModuleMap){

        for(int i = 0;i < dtoList.size();i++){
            AclModuleLevelDto dto = dtoList.get(i);
            String nextLevel = LevelUtil.calculateLevel(level,dto.getId());
            List<AclModuleLevelDto> tempList = (List<AclModuleLevelDto>) levelAclModuleMap.get(nextLevel);
            if(CollectionUtils.isNotEmpty(tempList)){

                Collections.sort(tempList,aclModuleSeqComparator);
                dto.setAclModuleList(tempList);
                transformAclModuleTree(tempList, nextLevel, levelAclModuleMap);
            }
        }
    }

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
        // level -> [dept1, dept2, ...] 以 level 为 key ，后面存储的是dept的列表
        // List 的每一个结构都是 DeptLevelDto
        Multimap<String, DeptLevelDto> levelDeptMap = ArrayListMultimap.create();
        // 一级部门
        List<DeptLevelDto> rootList = Lists.newArrayList();

        for(DeptLevelDto dto : deptLevelList) {
            levelDeptMap.put(dto.getLevel(), dto);
            if(LevelUtil.ROOT.equals(dto.getLevel())){
                rootList.add(dto);
            }
        }
        //按照 seq 从小到大排序
        // Comparator 比较器
        Collections.sort(rootList, new Comparator<DeptLevelDto>() {
            @Override
            public int compare(DeptLevelDto o1, DeptLevelDto o2) {
                return o1.getSeq() - o2.getSeq();
            }
        });
        //递归生成树 每遍历一次都会对当前树形结构进行处理
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

    /**
     * 将部门根据 seq 进行排序
     */
    public Comparator<DeptLevelDto> deptSeqComparator = new Comparator<DeptLevelDto>() {
        @Override
        public int compare(DeptLevelDto o1, DeptLevelDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

    /**
     * 将权限根据 seq 进行排序
     */
    public Comparator<AclModuleLevelDto> aclModuleSeqComparator = new Comparator<AclModuleLevelDto>() {
        @Override
        public int compare(AclModuleLevelDto o1, AclModuleLevelDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };
}
