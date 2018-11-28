package com.esc.fms.controller.right;

import com.esc.fms.entity.Role;
import com.esc.fms.entity.RoleRefPermission;
import com.esc.fms.service.right.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.List;

/**
 * Created by tangjie on 2017/2/11.
 */

@Controller
@RequestMapping("/role")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @RequestMapping("/view.do")
    public ModelAndView gotoRoleListPage()
    {
        return new ModelAndView("right/role");
    }

    @RequestMapping("/getRoleList.do")
    @ResponseBody
    public ModelMap getRoleList(@RequestParam(value = "Role",required = false) String role,
                                  @RequestParam(value = "page") int pageIndex,
                                  @RequestParam(value = "rows") int pageSize)
    {
        int offset = (pageIndex-1)*pageSize;
        ModelMap result = new ModelMap();
        result.put("total",roleService.getCountByConditions(role));
        result.put("rows",roleService.getRoleList(role,offset,pageSize));
        return result;
    }

    @RequestMapping("/getAllRole.do")
    @ResponseBody
    public List<Role> getAllRole()
    {
        return roleService.getAllRole();
    }

    @RequestMapping("/addRole")
    @ResponseBody
    public ModelMap addRole(@RequestParam("Role") String rolename,
                             @RequestParam("Permissions") List<Integer> permissions,
                             @RequestParam("Description") String description)
    {
        ModelMap result = new ModelMap();

        boolean success = true;
        String msg = "角色信息添加成功：" + rolename;

        Role role = new Role();
        role.setRole(rolename);
        role.setDescription(description);
        role.setEnabled(true);

        roleService.addRole(role,permissions);

        result.addAttribute("success", success);
        result.addAttribute("msg",msg);
        return result;
    }

    @RequestMapping("/getRolePermissionByPrimaryKey")
    @ResponseBody
    public ModelMap getRolePermissionByPrimaryKey(@RequestParam("RoleID") Integer roleID)
    {
        ModelMap result = new ModelMap();

        Role role = roleService.selectByPrimaryKey(roleID);
        List<RoleRefPermission> rrps = roleService.getPermissionByRoleID(roleID);

        result.put("Role",role);
        result.put("permissions",rrps);

        return result;
    }

    @RequestMapping("/editRole")
    @ResponseBody
    public ModelMap editRole(@RequestParam("RoleID") Integer roleID,@RequestParam("Role") String rolename,
                             @RequestParam("OldPermissions") List<Integer> oldPermissions,@RequestParam("NewPermissions") List<Integer> newPermissions,
                              @RequestParam("Description") String description)
    {
        ModelMap result = new ModelMap();

        boolean success = true;
        String msg = "角色信息修改成功：" + rolename;

        Role role = new Role();
        role.setRoleID(roleID);
        role.setRole(rolename);
        role.setDescription(description);
        role.setEnabled(true);

        roleService.editRole(role,oldPermissions,newPermissions);

        result.addAttribute("success", success);
        result.addAttribute("msg",msg);
        return result;
    }

    @RequestMapping("/delRole")
    @ResponseBody
    public ModelMap delRole(@RequestParam("RoleIDs") List<Integer> roles)
    {
        ModelMap result = new ModelMap();

        boolean success = true;
        String msg = "员工信息删除成功";

        roleService.disableRole(roles);

        result.addAttribute("success", success);
        result.addAttribute("msg",msg);
        return result;
    }

}
