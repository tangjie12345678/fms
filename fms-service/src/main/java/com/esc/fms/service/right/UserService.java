package com.esc.fms.service.right;

import com.esc.fms.entity.User;
import com.esc.fms.entity.UserListElement;

import java.util.List;
import java.util.Set;

/**
 * Created by tangjie on 2016/11/27.
 */
public interface UserService {

    int insert(User user);

    User getUserByUserName(String userName);

    Set<String> getRolesByUserName(String userName);

    Set<String> getPermissionsByUserName(String userName);

    List<UserListElement> getUserList(String userName,String staffName, int offset,int pageSize);

    Integer getCountByConditions(String userName,String staffName);

    boolean addUser(User user,List<Integer> roles);

    User selectByPrimaryKey(Integer userID);

    boolean editUser(User user,List<Integer> oldRoles,List<Integer> newRoles);

    boolean disableUser(List<Integer> userIDs);

    int authenticateUser(String userName, String password);

    int modifyPasswordByUserName(String userName, String password);

}
