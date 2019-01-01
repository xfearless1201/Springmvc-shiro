package com.tx.app.shiro;

import com.tx.app.entity.User;
import com.tx.app.redis.RedisClient;
import com.tx.app.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  *  @ClassName CustomRealm
 *  *  @Description 自定义realm类
 *  *  @Author Hardy
 *  *  @Date 2018年12月06日 13:13
 *  *  @Version 1.0.0
 *  
 **/
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private RedisCacheManager redisCacheManager;

    /**
     * 功能描述:
     * 授权
     * @Author: Hardy
     * @Date: 2018年12月07日 11:11:36
     * @param principalCollection
     * @return: org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
        // (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            doClearCache(principalCollection);
            SecurityUtils.getSubject().logout();
            return null;
        }
        //自定义权限标识
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    /**
     * 功能描述:
     * 认证
     * @Author: Hardy
     * @Date: 2018年12月06日 14:57:25
     * @param authenticationToken
     * @return: org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        SimpleAuthenticationInfo authenticationInfo = null;
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        if (StringUtils.isEmpty(username)){
            return null;
        }
        //通过用户名称查询用户信息
        User user = userService.getUserByUsername(username);
        if (user == null){
            //查询用户失败
            throw new UnknownAccountException();
        }

        /*if("0".equals(user.getIsDelete())){
            //用户禁止使用
            throw new DisabledAccountException();
        }*/
        //获取用户密码
        String password = user.getPassword();
        authenticationInfo = new SimpleAuthenticationInfo(username,password,getName());
        SecurityUtils.getSubject().getSession().setAttribute("uid",user.getUid());
        return authenticationInfo;
    }
}
