package com.tx.app.shiro;

import cn.hutool.json.JSONUtil;
import com.tx.app.commons.ResultResponse;
import com.tx.app.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  *  @ClassName SessionUtils
 *  *  @Description 缓存工具类
 *  *  @Author Hardy
 *  *  @Date 2018年12月06日 13:25
 *  *  @Version 1.0.0
 *  
 **/
public class SessionUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionUtils.class);

    /**
     * 获取登陆的key,即用户名
     *
     * @return
     */
    public static String getLoginKey() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return (String)subject.getPrincipal();
        }
        return null;
    }

    /**
     * 获取当前登陆用户
     *
     * @return
     */
    public static User getLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            Session session = subject.getSession();
            System.out.println(JSONUtil.toJsonStr(session));
            Object loginUser = session.getAttribute("lastUrlKey");
            return loginUser == null ? null : (User) loginUser;
        }
        return null;
    }

    /**
     * 获取当前登陆用户id
     *
     * @return
     */
    public static Long getLoginUserId() {
        User user = getLoginUser();
        if (user != null) {
            return user.getUid().longValue();
        }
        return null;
    }

    /**
     * 获取当前用户是否登陆
     *
     * @return
     */
    public static Boolean isLoggedIn() {
        boolean isLoggedIn = SecurityUtils.getSubject().isAuthenticated();
        return isLoggedIn;
    }

    /**
     * 功能描述:
     * 登录工具类
     * @Author: Hardy
     * @Date: 2018年12月06日 15:06:55
     * @param userName
     * @param password
     * @return: com.tx.app.commons.ResultResponse
     **/
    public static ResultResponse login(String userName,String password){
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(userName, password);
        try {
            subject.login(token);
            return ResultResponse.success("登录成功");
        } catch (UnknownAccountException ue) {
            LOGGER.error("用户不存在：{}", userName);
            return ResultResponse.success("用户不存在");
        } catch (LockedAccountException le) {
            LOGGER.error("用户名重复");
            return ResultResponse.success("用户名重复,请联系客服");
        } catch (IncorrectCredentialsException ie) {
            LOGGER.error("密码错误");
            return ResultResponse.success("密码错误");
        } catch (Exception e) {
            LOGGER.error("登陆出错:{}", e.getLocalizedMessage());
            return ResultResponse.success("登录出错:"+e.getLocalizedMessage());
        }
    }

    /**
     * 用户退出登陆
     */
    public static void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
