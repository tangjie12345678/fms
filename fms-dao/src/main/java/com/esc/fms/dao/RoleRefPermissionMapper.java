package com.esc.fms.dao;

import com.esc.fms.entity.RoleRefPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleRefPermissionMapper {
    int deleteByPrimaryKey(Integer recordID);

    int insert(RoleRefPermission record);

    int insertSelective(RoleRefPermission record);

    RoleRefPermission selectByPrimaryKey(Integer recordID);

    int updateByPrimaryKeySelective(RoleRefPermission record);

    int updateByPrimaryKey(RoleRefPermission record);

    List<RoleRefPermission> selectByRoleID(Integer roleID);

    int deleteByRoleIDPermissionID(@Param("roleID") Integer roleID,@Param("permissionID") Integer permissionID);
}