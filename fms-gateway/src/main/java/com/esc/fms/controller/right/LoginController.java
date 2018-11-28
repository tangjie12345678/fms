package com.esc.fms.controller.right;

import com.esc.fms.common.util.PasswordUtil;
import com.esc.fms.entity.SysMenu;
import com.esc.fms.service.right.PermissionService;
import com.esc.fms.service.right.SysMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tangjie on 2016/11/23.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private HttpSession session;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/loginIn.do")
    @ResponseBody
    public ModelMap loginOn(ModelMap model, String userName, String password)
    {
        logger.debug("用户登录：" + userName);
        ModelMap result = new ModelMap();

        boolean success = false;
        String msg = "登录失败：" + userName;

        password = PasswordUtil.execMD5Encode(new String(password));

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

        try {
            currentUser.login(token);
            success = true;
            logger.debug("用户登录验证成功：" + userName);
            msg = "用户登录验证成功：" + userName;

            setSession(userName);

        }catch (UnknownAccountException e){
            msg = "用户名或者密码错误！";
        }catch (LockedAccountException e){
            msg = e.getMessage();
        }catch (DisabledAccountException e){
            msg = e.getMessage();
        }catch (Exception e){
            logger.debug("用户登录异常:" + e.getMessage());
            msg = "用户登录异常:" + e.getMessage();
        }

        result.addAttribute("success", success);
        result.addAttribute("msg",msg);
        return result;
    }

    private void setSession(String userName)
    {
        List<SysMenu> menuList = new ArrayList<SysMenu>();
        menuList = sysMenuService.selectByUserName(userName);
        Map<String,List<String>> permissionMaps = permissionService.getPermissionsByUserName(userName);

        session.setAttribute("menuList",menuList);
        session.setAttribute("userName",userName);
        session.setAttribute("permissionMaps",permissionMaps);

        logger.debug("成功保存session!");
    }

}
