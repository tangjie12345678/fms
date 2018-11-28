package com.esc.fms.dao;

import com.esc.fms.entity.UserRefRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRefRoleMapper {
    int deleteByPrimaryKey(Integer recordID);

    int insert(UserRefRole record);

    int insertSelective(UserRefRole record);

    UserRefRole selectByPrimaryKey(Integer recordID);

    int updateByPrimaryKeySelective(UserRefRole record);

    int updateByPrimaryKey(UserRefRole record);

    List<UserRefRole> selectByUserID(Integer userID);

    int deleteByUserIDRoleID(@Param("userID") Integer userID,@Param("roleID") Integer roleID);
}