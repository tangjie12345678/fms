package com.esc.fms.service.right;

import com.esc.fms.entity.Role;
import com.esc.fms.entity.RoleRefPermission;

import java.util.List;

/**
 * Created by tangjie on 2017/2/11.
 */
public interface RoleService {

    List<Role> getRoleList(String role, int offset,int pageSize);

    boolean addRole(Role role,List<Integer> permissions);

    Role selectByPrimaryKey(Integer roleID);

    List<RoleRefPermission> getPermissionByRoleID(Integer roleID);

    boolean editRole(Role role,List<Integer> oldPermissions,List<Integer> newPermissions);

    boolean disableRole(List<Integer> roleIDs);

    Integer getCountByConditions(String role);

    List<Role> getAllRole();
}
