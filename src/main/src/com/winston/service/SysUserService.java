package com.winston.service;

import com.google.common.base.Preconditions;
import com.winston.dao.SysUserMapper;
import com.winston.exception.ParamException;
import com.winston.model.SysUser;
import com.winston.param.UserParam;
import com.winston.util.BeanValidator;
import com.winston.util.MD5Util;
import com.winston.util.PasswordUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @Author: 于新泽
 * @Date: Created in 9:18 2018/10/4.
 * @site :
 */

@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    public void save(UserParam param){

        BeanValidator.check(param);
        if(checkTelephoneExist(param.getTelephone(), param.getId())){
            throw new ParamException("电话已被占用");
        }
        if(checkEmailExist(param.getMail(), param.getId())){
            throw new ParamException("邮箱已被占用");
        }

        String password = PasswordUtil.randomPassword();
        // TODO:
        password = "12345678";
        String encryptedPassword = MD5Util.encrypt(password);
        SysUser user = SysUser.builder()
                .username(param.getUsername())
                .telephone(param.getTelephone())
                .password(encryptedPassword)
                .deptId(param.getDeptId())
                .status(param.getStatus())
                .remark(param.getRemark())
                .build();
        user.setOperator("system");
        user.setOperator("127.0.0.1");
        //user.setOperator(new Date());
        // TODO: sendEmail

        sysUserMapper.insertSelective(user);
    }

    public void update(UserParam param){
        BeanValidator.check(param);
        if(checkEmailExist(param.getMail(), param.getId())){
            throw new ParamException("邮箱已被占用");
        }
        if(checkTelephoneExist(param.getTelephone(), param.getId())){
            throw new ParamException("电话已被占用");
        }
        SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的用户不存在");
        SysUser after = SysUser.builder()
                .id(param.getId())
                .username(param.getUsername())
                .telephone(param.getTelephone())
                .deptId(param.getDeptId())
                .status(param.getStatus())
                .remark(param.getRemark())
                .build();
        sysUserMapper.updateByPrimaryKeySelective(after);
    }

    public boolean checkEmailExist(String mail, Integer userId){
        return sysUserMapper.countByMail(mail, userId) > 0;
    }

    public boolean checkTelephoneExist(String telephone, Integer userId){
        return sysUserMapper.countByTelephone(telephone, userId) > 0;
    }

    /**
     * 同时支持有手机号登录和邮箱登录
     * @param keyword
     * @return
     */
    public SysUser findByKeyword(String keyword) {
        return sysUserMapper.findByKeyword(keyword);
    }
}


