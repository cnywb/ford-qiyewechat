package com.ford.qiye.web.shiro;

import com.ford.qiye.lisenter.LogUtil;
import com.ford.qiye.model.DtUser;
import com.ford.qiye.model.OperationType;
import com.ford.qiye.service.LoginService;
import com.fute.common.util.DateUtils;
import com.fute.common.util.Md5Util;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by wanglijun on 16/8/5.
 */
@Component(value = "securityRealm")
public class SecurityRealm extends AuthorizingRealm {

    private static final Logger logger= LoggerFactory.getLogger (SecurityRealm.class);

    @Resource
    private LoginService loginService;

    //@Resource
    //private RoleService roleService;

  // @Resource
   /// private PermissionService permissionService;

    /**
     * 权限检查
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = String.valueOf(principals.getPrimaryPrincipal());

       // final User user = userService.selectByUsername(username);
      //  final List<Role> roleInfos = roleService.selectRolesByUserId(user.getId());
//        for (Role role : roleInfos) {
//            // 添加角色
//            System.err.println(role);
//            authorizationInfo.addRole(role.getRoleSign());
//
//            final List<Permission> permissions = permissionService.selectPermissionsByRoleId(role.getId());
//            for (Permission permission : permissions) {
//                // 添加权限
//                System.err.println(permission);
//                authorizationInfo.addStringPermission(permission.getPermissionSign());
//            }
//        }
//        return authorizationInfo;
        return null;
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        // 通过数据库进行验证
        final DtUser dtUser=loginService.doLogin (username);
        // 没找到帐号
        if (dtUser == null) {
            LogUtil.writer (username,dtUser.getRealName (), OperationType.USER_LOGIN_UNKNOWN_USER,dtUser.getUserName ());
             throw new UnknownAccountException ();
        }
        String credentials= Md5Util.getMD5(password);

        logger.info ("credentials:{},password:{}",credentials,password);
        //如果密码不相等则返回
        if(!credentials.equals (dtUser.getPassword ())){
            LogUtil.writer (dtUser.getUserName (), dtUser.getRealName (), OperationType.USER_LOGIN_PASSWORD_FAIL,dtUser.getUserName ());
            throw new CredentialsException ();
        }
        //判断密码是否过期了,
        Date today=new Date();
        if(today.after (dtUser.getCredentialExpiredDate ())){
            LogUtil.writer (dtUser.getUserName (),dtUser.getRealName (), OperationType.USER_PASSWORD_EXPIRED,dtUser.getUserName ());
            throw new CredentialsException ();
        }
        LogUtil.writer (dtUser.getUserName (), dtUser.getRealName (), OperationType.USER_LOGIN,dtUser.getUserName ());
        long expiredDay = DateUtils.getBetweenDay(new Date(), dtUser.getCredentialExpiredDate ());
        if (expiredDay <= 15) {
            dtUser.setExpiredDay (expiredDay);
        }
        return new SimpleAuthenticationInfo(dtUser,password,getName());
    }

}