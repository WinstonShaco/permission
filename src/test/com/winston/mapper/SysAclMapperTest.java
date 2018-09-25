package com.winston.mapper;

import com.winston.dao.SysAclMapper;
import com.winston.model.SysAcl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: 于新泽
 * @Date: Created in 23:06 2018/9/18.
 * @site :
 */

public class SysAclMapperTest {

    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception{
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    @Test
    public void selectByPrimaryKey() throws Exception{
        SysAclMapper sysAclMapper = (SysAclMapper) applicationContext.getBean("sysAclMapper");

        SysAcl sysAcl = sysAclMapper.selectByPrimaryKey(1);

        System.out.println(sysAcl);
    }
}
