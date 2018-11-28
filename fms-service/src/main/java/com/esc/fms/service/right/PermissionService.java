package com.esc.fms.service.right;


import com.esc.fms.entity.MenuPermission;
import com.esc.fms.entity.TreeNode;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Created by tangjie on 2017/2/12.
 */
public interface PermissionService {

    List<TreeNode> getMenuPermissionList();

    Map<String,List<String>> getPermissionsByUserName(String userName);

}
