package com.esc.fms.service.right.impl;

import com.esc.fms.common.util.PasswordUtil;
import com.esc.fms.entity.User;
import com.esc.fms.service.right.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tangjie on 2016/11/9.
 */

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

//    @Test
//    public void testSelectByPrimaryKey()
//    {
//        Integer id = 1;
//        User user = null;
//        try{
//            user = userService.selectByPrimaryKey(id);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        System.out.println(user);
//    }

//    @Test
//    public void testInsertSelective()
//    {
//        User user = new User();
//        user.setUserID("U002");
//        user.setUserName("name2");
//        try{
//            userService.insertSelective(user);
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//            System.out.println(e.getMessage());
//        }
//
//    }

//    @Test
//    public void testGetPasswordByUserID()
//    {
//        String userID = "U001";
//        String pwd = userService.getUserByUserName(userID);
//        System.out.println(pwd);
//
//    }

    @Test
    public void testInsert()
    {
        User user = new User();
        user.setUserName("admin");
        String pwd = PasswordUtil.execMD5Encode("123");
        user.setPassword(pwd);
        user.setDescription("用户管理员！");
        user.setLocked(false);
        user.setEnabled(false);
//        userService.insert(user);
    }

}
