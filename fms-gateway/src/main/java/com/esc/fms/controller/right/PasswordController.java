package com.esc.fms.controller.right;

import com.esc.fms.common.util.PasswordUtil;
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

/**
 * Created by tangjie on 2017/3/7.
 */
@Controller
@RequestMapping("/pwd")
public class PasswordController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class) ;

    @Autowired
    private UserService userService;

    @RequestMapping("/view.do")
    public ModelAndView gotoPwdModifyPage(){
        return new ModelAndView("right/pwdModify");
    }

    @RequestMapping("/authenticate.do")
    @ResponseBody
    public ModelMap authenticate(@RequestParam("UserName") String userName,@RequestParam("Password") String password)
    {
        ModelMap result = new ModelMap();

        boolean success = true;
        String msg = "用户验证成功！";

        int count = userService.authenticateUser(userName,PasswordUtil.execMD5Encode(password));

        if(count == 0)
        {
            success = false;
            msg = "用户验证失败，用户名或密码错误！";
        }else if(count > 1){
            success = false;
            msg = "用户验证失败，请联系管理员！";
            logger.warn("系统存在多个用户名和密码相同的账户，用户名：{}",userName);
        }

        result.put("success",success);
        result.put("msg",msg);
        return result;
    }


    @RequestMapping("/modifyPassword.do")
    @ResponseBody
    public ModelMap modifyPassword(@RequestParam("UserName") String userName,@RequestParam("Password") String password)
    {
        ModelMap result = new ModelMap();

        boolean success = true;
        String msg = "密码修改成功！";

        int count = userService.modifyPasswordByUserName(userName,PasswordUtil.execMD5Encode(password));

        if (count == 0){
            success = false;
            msg = "密码修改失败，请联系系统管理员！";
        }else if (count > 1){
            success = false;
            msg = "密码修改出现异常，请联系系统管理员！";
            logger.warn("多个用户的密码被修改，系统存在同名用户，用户名{}",userName);
        }

        result.put("success",success);
        result.put("msg",msg);
        return result;
    }
}
