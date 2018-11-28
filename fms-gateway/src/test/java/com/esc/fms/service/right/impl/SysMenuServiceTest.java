package com.esc.fms.service.right.impl;

import com.esc.fms.entity.SysMenu;
import com.esc.fms.service.right.SysMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangjie on 2016/12/6.
 */
@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SysMenuServiceTest {

    @Autowired
    private SysMenuService sysMenuService;


    @Test
    public void testSelectByUserName()
    {
        String userName = "admin";
        List<SysMenu> menulist = new ArrayList<SysMenu>();
        menulist = sysMenuService.selectByUserName(userName);

        for(SysMenu menu : menulist){
            System.out.println(menu);
        }
    }

}
