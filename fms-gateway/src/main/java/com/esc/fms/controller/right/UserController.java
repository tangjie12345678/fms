package com.esc.fms.controller.right;

import com.esc.fms.common.util.PasswordUtil;
import com.esc.fms.entity.User;
import com.esc.fms.entity.UserRefRole;
import com.esc.fms.service.right.UserRefRoleService;
import com.esc.fms.service.right.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by tangjie on 2017/2/22.
 */


@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class) ;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRefRoleService userRefRoleService;

    @RequestMapping("/view.do")
    public ModelAndView gotoUserListPage(){
        return new ModelAndView("right/user");
    }

    @RequestMapping("/getUserList.do")
    @ResponseBody
    public ModelMap getUserList(@RequestParam(value = "UserName",required = false) String userName,
                                @RequestParam(value = "StaffName",required = false) String staffName,
                                @RequestParam(value = "page") int pageIndex,
                                @RequestParam(value = "rows") int pageSize)
    {
        int offset = (pageIndex-1)*pageSize;
        ModelMap result = new ModelMap();
        logger.debug("call method getUserList: UserName={},StaffName={}",userName,staffName);
        result.put("total",userService.getCountByConditions(userName, staffName));
        result.put("rows",userService.getUserList(userName, staffName,offset,pageSize));
        return result;

    }

    @RequestMapping("/addUser.do")
    @ResponseBody
    public ModelMap addUser(@RequestParam("UserName") String userName,@RequestParam("Password") String password,
                             @RequestParam("StaffRecordID") Integer staffRecordID,@RequestParam("Roles") List<Integer> roles,@RequestParam("Description") String description)
    {
        ModelMap result = new ModelMap();

        boolean success = true;
        String msg = "用户信息添加成功：" + userName;

        User user = new User();

        user.setUserName(userName);
        user.setPassword(PasswordUtil.execMD5Encode(password));
        user.setStaffRecordID(staffRecordID);
        user.setDescription(description);
        user.setLocked(false);
        user.setEnabled(true);

        userService.addUser(user,roles);

        result.addAttribute("success", success);
        result.addAttribute("msg",msg);
        return result;
    }

    @RequestMapping("/getUserInfoByPrimaryKey.do")
    @ResponseBody
    public ModelMap getUserInfoByPrimaryKey(@RequestParam("UserID") Integer userID)
    {
        ModelMap result = new ModelMap();

        User user = userService.selectByPrimaryKey(userID);
        user.setPassword(null);
        List<UserRefRole> userRefRoles = userRefRoleService.selectByUserID(userID);

        result.put("User",user);
        result.put("UserRefRoles",userRefRoles);

        return result;
    }

    @RequestMapping("/editUser.do")
    @ResponseBody
    public ModelMap editUser(@RequestParam("UserID") Integer userID,@RequestParam("UserName") String userName,
                              @RequestParam("OldRoles") List<Integer> oldRoles,@RequestParam("NewRoles") List<Integer> newRoles,
                              @RequestParam("Description") String description,@RequestParam("StaffRecordID") Integer staffRecordID)
    {
        ModelMap result = new ModelMap();

        boolean success = true;
        String msg = "用户信息修改成功：" + userName;

        User user = new User();

        user.setStaffRecordID(staffRecordID);
        user.setUserID(userID);
        user.setUserName(userName);
        user.setDescription(description);

        userService.editUser(user,oldRoles,newRoles);

        result.addAttribute("success", success);
        result.addAttribute("msg",msg);
        return result;
    }


    @RequestMapping("/delUser")
    @ResponseBody
    public ModelMap delUser(@RequestParam("UserIDs") List<Integer> userIDs)
    {
        ModelMap result = new ModelMap();

        boolean success = true;
        String msg = "员工信息删除成功";

        userService.disableUser(userIDs);

        result.addAttribute("success", success);
        result.addAttribute("msg",msg);
        return result;
    }
}
