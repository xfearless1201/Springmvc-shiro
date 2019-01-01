package com.tx.app.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.tx.app.commons.ResultResponse;
import com.tx.app.entity.User;
import com.tx.app.redis.RedisClient;
import com.tx.app.service.UserService;
import com.tx.app.shiro.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.awt.SunHints;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *  *  @ClassName UserController
 *  *  @Description 用户controller
 *  *  @Author Hardy
 *  *  @Date 2018年12月06日 12:31
 *  *  @Version 1.0.0
 *  
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisClient redisClient;

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public ResultResponse findAll(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(defaultValue = "0",required = false) Integer pageNo,
                              @RequestParam(defaultValue = "10",required = false) Integer pageSize){
        return ResultResponse.success("查询成功",null);
    }

    /**
     * 功能描述:
     * 获取用户详情
     * @Author: Hardy
     * @Date: 2018年12月06日 15:57:35
     * @param request
     * @param response
     * @return: com.tx.app.commons.ResultResponse
     **/
    @RequestMapping(value = "getUserInfo",method = RequestMethod.GET)
    public ResultResponse getUserInfo(HttpServletRequest request,HttpServletResponse response,String token){
        if(StrUtil.isBlank(token)){
            return ResultResponse.fail("token不能为空");
        }
        String uid = new String(redisClient.get(token.getBytes()));
        if(StrUtil.isBlank(uid)){
            return ResultResponse.fail("登录超时,重新登录");
        }
        //查询用户详情
        User u = userService.getUserById(Integer.parseInt(uid));
        return ResultResponse.success("查询成功",u);
    }
}
