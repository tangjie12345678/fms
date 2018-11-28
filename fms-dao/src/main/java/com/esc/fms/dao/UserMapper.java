package com.esc.fms.dao;

import com.esc.fms.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userID);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userID);

    User getUserByUserName(String userName);

    Set<String> getRolesByUserName(String userName);

    Set<String> getPermissionsByUserName(String userName);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int disableUser(Integer userID);

    int authenticateUser(@Param("userName") String userName,@Param("password") String password);

    int modifyPasswordByUserName(@Param("userName") String userName,@Param("password") String password);
}