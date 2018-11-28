package com.esc.fms.service.base.impl;

import com.esc.fms.entity.FtpConnectionInfo;
import com.esc.fms.service.base.FtpConnectionInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tangjie on 2017/7/22.
 */
@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class FtpConnectionInfoServiceTest {

    @Autowired
    private FtpConnectionInfoService ftpConnectionInfoService;

    @Test
    public void getMasterFtpConnectionInfoTest(){

        FtpConnectionInfo obj = ftpConnectionInfoService.getMasterFtpConnectionInfo();
        System.out.println(obj.getHostIP());

    }

}
