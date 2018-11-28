package com.esc.fms.service.right.impl;

import com.esc.fms.entity.TreeNode;
import com.esc.fms.service.right.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by tangjie on 2017/2/14.
 */
@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PermissionServiceTest {

    @Autowired
    private PermissionService permissionService;

    @Test
    public void getPermissionList(){

        List<TreeNode> nodes = permissionService.getMenuPermissionList();

        System.out.println("test");

    }

    @Test
    public void getPermissionsByUserNameTest(){
        String userName = "tom";
        Map<String,List<String>> permissionMap =  permissionService.getPermissionsByUserName(userName);
    }

}
