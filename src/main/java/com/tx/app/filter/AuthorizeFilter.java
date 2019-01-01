package com.tx.app.filter;

import cn.hutool.json.JSONNull;
import cn.hutool.json.JSONUtil;
import com.tx.app.commons.AppConst;
import com.tx.app.commons.ResultResponse;
import com.tx.app.redis.RedisClient;
import com.tx.app.shiro.CookieUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

/**
 *  *  @ClassName AuthorizeFilter
 *  *  @Description 授权过滤类
 *  *  @Author Hardy
 *  *  @Date 2018年12月06日 13:11
 *  *  @Version 1.0.0
 *  
 **/
public class AuthorizeFilter extends AuthorizationFilter {

    @Autowired
    private RedisClient redisClient;

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated()) {
            return false;
        }
        Boolean isAjax = isAjax(request);
        if (subject.getPrincipal() != null && isAjax) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(AppConst.UNAUTHORIZED);
            Writer w = response.getWriter();
            w.write(JSONUtil.toJsonStr(ResultResponse.fail("无权限操作")));
            w.flush();
            w.close();
            return  false;
        }
        return super.onAccessDenied(servletRequest, servletResponse);
    }

    /**
     * 根据请求头判断是不是ajax请求
     *
     * @param request 请求实体
     *
     * @return
     */
    private Boolean isAjax(HttpServletRequest request) {
        Boolean isAjax = request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals( request.getHeader("X-Requested-With").toString());
        return isAjax;
    }

    /**
     * 判断用户是否可以访问请求的资源
     *
     * @param request 用户请求
     *
     * @param response 响应
     *
     * @param mappedValue
     *
     * @return
     *
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
                                      Object mappedValue) throws Exception {
        // 登陆请求直接放行
        if (isLoginRequest(request, response)) {
            return true;
        }
        // 获取用户认证信息
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated()) {
            return false;
        }

//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String token = CookieUtils.readLoginToken(httpServletRequest);
//        if (subject.isPermitted(token)){
//            return true;
//        }
        return true;
    }
}
