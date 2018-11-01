package com.winston.service;

import com.google.common.base.Preconditions;
import com.winston.beans.PageQuery;
import com.winston.beans.PageResult;
import com.winston.common.RequestHolder;
import com.winston.dao.SysAclMapper;
import com.winston.exception.ParamException;
import com.winston.model.SysAcl;
import com.winston.param.AclParam;
import com.winston.util.BeanValidator;
import com.winston.util.IpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: 于新泽
 * @Date: Created in 11:28 2018/10/30.
 * @site :
 */

@Service
public class SysAclService {

    @Resource
    private SysAclMapper sysAclMapper;

    /**
     * 新增权限点
     * @param param
     */
    public void save(AclParam param){
        // 参数校验
        BeanValidator.check(param);
        // 先做一个简单的判断
        if(checkExist(param.getAclModuleId(), param.getName(), param.getId())){
            throw new ParamException("当前权限模块下面存在相同名称的权限点");
        }
        SysAcl acl = SysAcl
                .builder()
                .name(param.getName())
                .aclModuleId(param.getAclModuleId())
                .url(param.getUrl())
                .type(param.getType())
                .status(param.getStatus())
                .seq(param.getSeq())
                .remark(param.getRemark())
                .build();
        acl.setCode(generateCode());
        acl.setOperator(RequestHolder.getCurrentUser().getUsername());
        acl.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequset()));
        sysAclMapper.insertSelective(acl);
    }

    /**
     * 更新权限点
     * @param param
     */
    public void update(AclParam param){
        // 参数校验
        BeanValidator.check(param);
        // 先做一个简单的判断
        if(checkExist(param.getAclModuleId(), param.getName(), param.getId())){
            throw new ParamException("当前权限模块下面存在相同名称的权限点");
        }
        SysAcl before = sysAclMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的权限点不存在");


        SysAcl after = SysAcl
                .builder()
                .name(param.getName())
                .aclModuleId(param.getAclModuleId())
                .url(param.getUrl())
                .type(param.getType())
                .status(param.getStatus())
                .seq(param.getSeq())
                .remark(param.getRemark())
                .build();
        after.setCode(generateCode());
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequset()));

        sysAclMapper.updateByPrimaryKeySelective(after);
    }

    /**
     * 权限点名称校验
     * 同一父权限下不能有形同名称的权限点
     * @param aclModuleId
     * @param name
     * @param id
     * @return
     */
    public boolean checkExist(int aclModuleId, String name, Integer id){
        return  sysAclMapper.countByNameAndAclModuleId(aclModuleId, name, id) > 0;
    }

    /**
     * 生成一个Code值
     * @return
     */
    public String generateCode(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date()) + "_" + (int)(Math.random() * 100);
    }

    /**
     * 返回的权限点
     * @param aclModuleId
     * @param page ： 分页信息
     * @return
     */
    public PageResult<SysAcl> getPageByAclModuleId(int aclModuleId, PageQuery page){
        BeanValidator.check(page);
        int count = sysAclMapper.countByAclModuleId(aclModuleId);
        if(count > 0){
            List<SysAcl> aclList = sysAclMapper.getPageByAclModuleId(aclModuleId, page);
            return PageResult.<SysAcl>builder().data(aclList).total(count).build();
        }
        return PageResult.<SysAcl>builder().build();
    }
}
