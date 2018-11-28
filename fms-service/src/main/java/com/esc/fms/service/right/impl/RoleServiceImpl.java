package com.esc.fms.service.right.impl;

import com.esc.fms.dao.RoleMapper;
import com.esc.fms.dao.RoleRefPermissionMapper;
import com.esc.fms.entity.Permission;
import com.esc.fms.entity.Role;
import com.esc.fms.entity.RoleRefPermission;
import com.esc.fms.service.right.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangjie on 2017/2/11.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleRefPermissionMapper roleRefPermissionMapper;

    public List<Role> getRoleList(String role, int offset,int pageSize) {
        return roleMapper.getRoleList(role,offset,pageSize);
    }

    public boolean addRole(Role role, List<Integer> permissions) {

        int count = roleMapper.insert(role);

        int roleID = role.getRoleID();

        for(Integer permissionID : permissions){
            RoleRefPermission rrp = new RoleRefPermission();
            rrp.setRoleID(roleID);
            rrp.setPermissionID(permissionID);
            count = roleRefPermissionMapper.insert(rrp);
        }

        return true;
    }

    public Role selectByPrimaryKey(Integer roleID) {
        return roleMapper.selectByPrimaryKey(roleID);
    }

    public List<RoleRefPermission> getPermissionByRoleID(Integer roleID) {
        return roleRefPermissionMapper.selectByRoleID(roleID);
    }

    public boolean editRole(Role role, List<Integer> oldPermissions, List<Integer> newPermissions) {

        int count = roleMapper.updateByPrimaryKey(role);



        if(newPermissions.equals(oldPermissions))
        {
            logger.debug("角色的权限信息并未发生改变！");
        }
        else
        {
            logger.debug("角色的权限信息发生改变！");

            List<Integer> intersection = new ArrayList<Integer>();

            for(Integer p : oldPermissions)
            {
                if(newPermissions.contains(p))
                {
                    intersection.add(p);
                }
            }

            oldPermissions.removeAll(intersection);

            if(!oldPermissions.isEmpty())
            {
                for(Integer permissionID: oldPermissions){
                    count = roleRefPermissionMapper.deleteByRoleIDPermissionID(role.getRoleID(),permissionID);
                }
            }

            newPermissions.removeAll(intersection);
            if(!newPermissions.isEmpty())
            {
                for(Integer permissionID : newPermissions){
                    RoleRefPermission rrp = new RoleRefPermission();
                    rrp.setRoleID(role.getRoleID());
                    rrp.setPermissionID(permissionID);
                    count = roleRefPermissionMapper.insert(rrp);
                }
            }

        }



        return true;
    }

    public boolean disableRole(List<Integer> roleIDs) {

        int count = 0;

        for(Integer roleID : roleIDs){
            count = roleMapper.disableRole(roleID);
        }

        return true;
    }

    public Integer getCountByConditions(String role) {
        return roleMapper.getCountByConditions(role);
    }

    public List<Role> getAllRole() {
        return roleMapper.getAllRole();
    }


}
