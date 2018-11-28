package com.esc.fms.controller.right;

import com.esc.fms.entity.MenuPermission;
import com.esc.fms.entity.Role;
import com.esc.fms.entity.TreeNode;
import com.esc.fms.service.right.PermissionService;
import com.esc.fms.service.right.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangjie on 2017/2/12.
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private PermissionService permissionService;



    @RequestMapping("/getPermissionList")
    @ResponseBody
    public List<TreeNode> getPermissionList()
    {
        return permissionService.getMenuPermissionList();
    }



}
