package com.esc.fms.service.right;

import com.esc.fms.entity.UserRefRole;

import java.util.List;

/**
 * Created by tangjie on 2017/3/2.
 */
public interface UserRefRoleService {

    List<UserRefRole> selectByUserID(Integer userID);

    int deleteByUserIDRoleID(Integer userID, Integer roleID);

    int insert(UserRefRole record);
}
