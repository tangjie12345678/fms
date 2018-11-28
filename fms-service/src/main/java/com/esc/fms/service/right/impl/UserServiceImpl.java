package com.esc.fms.service.right.impl;

import com.esc.fms.dao.UserListElementMapper;
import com.esc.fms.dao.UserMapper;
import com.esc.fms.dao.UserRefRoleMapper;
import com.esc.fms.entity.User;
import com.esc.fms.entity.UserListElement;
import com.esc.fms.entity.UserRefRole;
import com.esc.fms.service.right.UserRefRoleService;
import com.esc.fms.service.right.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by tangjie on 2016/11/27.
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserListElementMapper userListElementMapper;

    @Autowired
    private UserRefRoleService userRefRoleService;

    public int insert(User user){
        return userMapper.insert(user);
    }

    public User getUserByUserName(String userName){
        return userMapper.getUserByUserName(userName);
    }

    public Set<String> getRolesByUserName(String userName) {
        return userMapper.getRolesByUserName(userName);
    }

    public Set<String> getPermissionsByUserName(String userName) {
        return userMapper.getPermissionsByUserName(userName);
    }

    public List<UserListElement> getUserList(String userName, String staffName, int offset, int pageSize) {
        return userListElementMapper.getUserListByConditions(userName,staffName,offset,pageSize);
    }

    public Integer getCountByConditions(String userName, String staffName) {
        return userListElementMapper.getCountByConditions(userName,staffName);
    }

    public boolean addUser(User user, List<Integer> roles) {

        int count = userMapper.insert(user);

        for(Integer roleID : roles)
        {
            UserRefRole urr = new UserRefRole();
            urr.setRoleID(roleID);
            urr.setUserID(user.getUserID());

            userRefRoleService.insert(urr);
        }

        return true;
    }

    public User selectByPrimaryKey(Integer userID) {
        return userMapper.selectByPrimaryKey(userID);
    }

    public boolean editUser(User user, List<Integer> oldRoles, List<Integer> newRoles) {

        int count = userMapper.updateByPrimaryKeySelective(user);

        List<Integer> intersection = new ArrayList<Integer>();

        if(newRoles.equals(oldRoles))
        {
            logger.debug("用户所属角色并未发生改变！");
        }
        else
        {
            logger.debug("用户所属角色已经发生改变！");
            for(Integer roleID : oldRoles)
            {
                if(newRoles.contains(roleID))
                {
                    intersection.add(roleID);
                }
            }

            oldRoles.removeAll(intersection);

            if(!oldRoles.isEmpty())
            {
                for(Integer roleID: oldRoles){
                    count = userRefRoleService.deleteByUserIDRoleID(user.getUserID(),roleID);
                }
            }

            newRoles.removeAll(intersection);
            if(!newRoles.isEmpty())
            {
                for(Integer roleID : newRoles){
                    UserRefRole urr = new UserRefRole();
                    urr.setRoleID(roleID);
                    urr.setUserID(user.getUserID());
                    count = userRefRoleService.insert(urr);
                }
            }

        }

        return true;
    }

    public boolean disableUser(List<Integer> userIDs) {
        int count = 0;

        for(Integer userID : userIDs){
            count = userMapper.disableUser(userID);
        }

        return true;
    }

    public int authenticateUser(String userName, String password) {
        return userMapper.authenticateUser(userName,password);
    }

    public int modifyPasswordByUserName(String userName, String password) {
        return userMapper.modifyPasswordByUserName(userName,password);
    }


}
