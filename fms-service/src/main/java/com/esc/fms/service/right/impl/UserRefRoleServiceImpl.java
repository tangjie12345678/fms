package com.esc.fms.service.right.impl;

import com.esc.fms.dao.UserRefRoleMapper;
import com.esc.fms.entity.UserRefRole;
import com.esc.fms.service.right.UserRefRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tangjie on 2017/3/2.
 */

@Service("userRefRoleSecrvie")
public class UserRefRoleServiceImpl implements UserRefRoleService {

    @Autowired
    private UserRefRoleMapper userRefRoleMapper;

    public List<UserRefRole> selectByUserID(Integer userID) {
        return userRefRoleMapper.selectByUserID(userID);
    }

    public int deleteByUserIDRoleID(Integer userID, Integer roleID) {
        return userRefRoleMapper.deleteByUserIDRoleID(userID,roleID);
    }

    public int insert(UserRefRole record) {
        return userRefRoleMapper.insert(record);
    }
}
