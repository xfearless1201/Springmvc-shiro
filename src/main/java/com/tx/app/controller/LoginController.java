package com.tx.app.controller;

import com.tx.app.commons.ResultResponse;
import com.tx.app.entity.User;
import com.tx.app.redis.RedisClient;
import com.tx.app.service.UserService;
import com.tx.app.shiro.CookieUtils;
import com.tx.app.shiro.SessionUtils;
import com.tx.app.shiro.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc:用户登陆登出控制器
 * Created by hafiz.zhang on 2017/7/21.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisClient redisClient;

    //日志
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "redirect:/index.html";
    }

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    /**
     * 功能描述:
     * 登录接口
     * @Author: Hardy
     * @Date: 2018年12月06日 14:59:25
     * @param username 登录用户名
     * @param password 登录密码
     * @return: com.tx.app.vo.JsonResult
     **/
    @RequestMapping(value = "/login.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse login(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(value = "username",required = true) String username,
                                @RequestParam(value = "password",required = true) String password) {
        try {
            if (username == null) {
                return ResultResponse.fail("用户名不能为空");
            }
            if (password == null) {
                return ResultResponse.fail("密码不能为空");
            }
            //加密
            password = ShiroUtils.desEncrypt(password);
            if (!SessionUtils.isLoggedIn()) {
                ResultResponse result = SessionUtils.login(username, password);
                if(result.getStatus() == 1){
                    //登录成功,生成缓存
                    User user = userService.getUserByUsername(username);
                    //查询用户ID
                    String uid = String.valueOf(user.getUid());
                    String token = ShiroUtils.md5Encrypt(uid);//MD5加密串
                    //token存入缓存
                    redisClient.set(token.getBytes(),uid.getBytes());//token为key uid 为value
                    //cookie中保存token
                    CookieUtils.writeLoginToken(token);
                }
                return result;
            }
            return ResultResponse.success("登录成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("登录异常:{}",e.getMessage());
            return ResultResponse.error("登录异常");
        }

    }

    @RequestMapping(value = "/logout.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse logout() {
        SessionUtils.logout();
        return ResultResponse.success("退出成功");
    }

    @RequestMapping(value = "/unauthorized.html", method = RequestMethod.GET)
    public String unauthorized() {
        return "unauthorized";
    }
}
