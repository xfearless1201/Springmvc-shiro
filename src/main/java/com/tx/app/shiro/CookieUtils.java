package com.tx.app.shiro;

import com.alibaba.druid.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  *  @ClassName CookieUtils
 *  *  @Description TODO(这里用一句话描述这个类的作用)
 *  *  @Author Hardy
 *  *  @Date 2018年12月07日 14:35
 *  *  @Version 1.0.0
 *  
 **/
public class CookieUtils {

    private static final Logger logger = LoggerFactory.getLogger(CookieUtils.class);

    private static final String COOKIE_NAME = "custom.session";

    public static void writeLoginToken(String token) {
        Cookie cookie = new Cookie(COOKIE_NAME, token);
        cookie.setPath("/");
        // 防止脚本攻击，不允许脚本访问 cookie
        cookie.setHttpOnly(true);
        // -1 代表永不过期， 单位 秒 如果 maxage cookie 则不会写入硬盘，只写入内存， 只在当前页面有效
        cookie.setMaxAge(60 * 60 * 24 * 30);
        logger.info("write cookie cookieName:" + cookie.getName() + " cookieValue:" + cookie.getValue());
        System.out.println("打印cookie日志:write cookie cookieName:" + cookie.getName() + " cookieValue:" + cookie.getValue());
    }

    public static String readLoginToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(), COOKIE_NAME)) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    public static void delLoginToken (HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(), COOKIE_NAME)) {
                    cookie.setPath("/");
                    // 設置為 0 代表刪除
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);

                    return;
                }
            }
        }
    }
}
