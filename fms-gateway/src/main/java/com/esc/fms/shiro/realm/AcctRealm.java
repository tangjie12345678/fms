package com.esc.fms.shiro.realm;

import com.esc.fms.entity.User;
import com.esc.fms.service.right.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tangjie on 2016/11/9.
 */
public class AcctRealm extends AuthorizingRealm{

    private static final Logger logger = LoggerFactory.getLogger(AcctRealm.class);

    @Autowired
    private UserService userService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        // 获取用户名
        String userName = (String)principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();

        simpleAuthorInfo.addRoles(userService.getRolesByUserName(userName));
        //添加权限
        simpleAuthorInfo.addStringPermissions(userService.getPermissionsByUserName(userName));

        logger.debug("成功获取用户授权信息：" + userName);
        return simpleAuthorInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        //用户填写的用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        String userName = token.getUsername();

        User user = userService.getUserByUserName(userName);
        if(user == null){
            logger.debug("用户不存在:" + userName);
            throw new UnknownAccountException("用户不存在:" + userName);//没找到帐号
        }else if(user.getLocked() == true){
            logger.debug("账号已被锁定:" + userName);
            throw new LockedAccountException("账号已被锁定:" + userName);
        }else if(user.getEnabled() == false){
            logger.debug("账号已被禁用:" + userName);
            throw new DisabledAccountException("账号已被禁用:" + userName);
        }

        logger.debug("成功获取用户信息：" + userName);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName,user.getPassword(), getName());
        return authenticationInfo;
    }

    @Override
    public String getName()
    {
        return getClass().getName();
    }


}
