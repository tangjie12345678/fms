package com.esc.fms.dao;

import com.esc.fms.entity.UserListElement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserListElementMapper {
    int insert(UserListElement record);

    int insertSelective(UserListElement record);

    List<UserListElement> getUserListByConditions(@Param("userName") String userName, @Param("staffName") String staffName,
                                         @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    Integer getCountByConditions(@Param("userName") String userName, @Param("staffName") String staffName);
}