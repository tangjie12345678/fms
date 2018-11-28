package com.esc.fms.dao;

import com.esc.fms.entity.Role;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleID);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleID);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getRoleList(@Param("role") String role,@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);

    int disableRole(Integer roleID);

    Integer getCountByConditions(@Param("role") String role);

    List<Role> getAllRole();
}