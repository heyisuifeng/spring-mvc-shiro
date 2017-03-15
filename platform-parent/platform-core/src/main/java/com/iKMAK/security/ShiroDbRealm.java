package com.iKMAK.security;

import com.iKMAK.model.Role;
import com.iKMAK.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Leaf.Ye on 2017/3/15.
 */
public class ShiroDbRealm extends AuthorizingRealm{

    @Inject
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) getAvailablePrincipal(principalCollection);//使用Shiro提供的方法获取用户名称
        if(username!=null){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
            List<Role> roles =
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
