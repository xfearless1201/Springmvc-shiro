package com.tx.app.service;

import com.tx.app.entity.User;

import java.util.List;

/**
 *  * @ClassName UserService
 *  * @Description 用户接口
 *  * @Author Hardy
 *  * @Date 2018年12月06日 12:18
 *  * @Version 1.0.0
 *  
 **/
public interface UserService {

    User getUserByUsername(String username);

    User getUserById(Integer uid);
}
