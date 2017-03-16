package com.iKMAK.security;

import com.iKMAK.model.Role;
import com.iKMAK.model.User;
import com.iKMAK.service.RoleService;
import com.iKMAK.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.CollectionUtils;

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
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<Role> roles = userService.getRolesByUsername(username);
            if (!CollectionUtils.isEmpty(roles)){
                //将该用户所拥有的角色加入
                for (Role role:roles) {
                    info.addRole(role.getRoleName());
                }
            }
            List<String> permTokens = userService.getPermTokensByUsername(username);
            if(!CollectionUtils.isEmpty(permTokens)){
                //将用户所拥有的权限加入
                info.addStringPermissions(permTokens);
            }
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取基于用户名和密码的令牌
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        if (username!=null&&!"".equals(username)){

            User user = userService.getUserByUsername(username);
            if (user!=null){
                //此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息
                //说白了就是第一个参数填登录用户名,第二个参数填合法的登录密码
                //这样一来,在随后的登录页面上就只有这里指定的用户和密码才能通过验证
                return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
            }
        }
        return null;
    }
}
