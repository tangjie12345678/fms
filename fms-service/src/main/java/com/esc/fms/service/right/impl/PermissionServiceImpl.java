package com.esc.fms.service.right.impl;

import com.esc.fms.dao.MenuPermissionMapper;
import com.esc.fms.dao.PermissionMapper;
import com.esc.fms.entity.MenuPermission;
import com.esc.fms.entity.Permission;
import com.esc.fms.entity.TreeNode;
import com.esc.fms.service.right.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangjie on 2017/2/12.
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private MenuPermissionMapper menuPermissionMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    public List<TreeNode> getMenuPermissionList() {

        List<MenuPermission> mps = menuPermissionMapper.getMenuPermissionList();

        List<TreeNode> ps = new ArrayList<TreeNode>();

        String preMenuName = "";

        TreeNode firstLevelMenu = null;
        TreeNode secondLevelMenu = null;

        for(MenuPermission mp : mps){
            if(mp.getParentID() == null){
                TreeNode parent = new TreeNode();
//                parent.setId("");
                parent.setId(String.valueOf(mp.getPermissionID()));
                parent.setText(mp.getMenuName());
                parent.setState("open");

//                TreeNode child = new TreeNode();
//                child.setId(String.valueOf(mp.getPermissionID()));
//                child.setText(mp.getPermissionDesc());

                List<TreeNode> children = new ArrayList<TreeNode>();
//                children.add(child);

                parent.setChildren(children);

                ps.add(parent);

                firstLevelMenu = parent;
            }
            else{
                if(!mp.getMenuName().equals(preMenuName))
                {
                    preMenuName = mp.getMenuName();
                    TreeNode parent = new TreeNode();
                    parent.setId("");
                    parent.setText(mp.getMenuName());
                    parent.setState("closed");

                    List<TreeNode> children = new ArrayList<TreeNode>();

                    TreeNode child = new TreeNode();
                    child.setId(String.valueOf(mp.getPermissionID()));
                    child.setText(mp.getPermissionDesc());

                    children.add(child);

                    parent.setChildren(children);
//                    ps.add(parent);
                    secondLevelMenu = parent;
                    firstLevelMenu.addChild(parent);
                }
                else{
                    TreeNode node = new TreeNode();
                    node.setId(String.valueOf(mp.getPermissionID()));
                    node.setText(String.valueOf(mp.getPermissionDesc()));

                    secondLevelMenu.addChild(node);
                }
            }

        }

        return ps;
    }

    public Map<String, List<String>> getPermissionsByUserName(String userName) {

        List<String> permissions = permissionMapper.getPermissionsByUserName(userName);

        Map<String,List<String>> permissionMap = new HashMap<String, List<String>>();

        List<String> modelPermissions = null;

        if(null != permissions && !permissions.isEmpty()){
            for(String permission : permissions){
                String[] arr = permission.split(":");
                modelPermissions = permissionMap.get(arr[0]);
                if(null == modelPermissions){
                    modelPermissions = new ArrayList<String>();
                    modelPermissions.add(arr[1]);
                    permissionMap.put(arr[0],modelPermissions);
                }else{
                    modelPermissions.add(arr[1]);
                }
            }
        }

        return permissionMap;
    }
}
