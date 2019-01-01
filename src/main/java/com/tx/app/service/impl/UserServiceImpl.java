package com.tx.app.service.impl;

import com.tx.app.commons.ResultResponse;
import com.tx.app.entity.User;
import com.tx.app.mapper.UserMapper;
import com.tx.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 *  *  @ClassName UserServiceImpl
 *  *  @Description TODO(这里用一句话描述这个类的作用)
 *  *  @Author Hardy
 *  *  @Date 2018年12月06日 12:29
 *  *  @Version 1.0.0
 *  
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        User users = userMapper.getUserByUsername(username);
        if(users == null){
            new ArrayList();
        }
        return users;
    }

    @Override
    public User getUserById(Integer uid) {
        return userMapper.selectByPrimkey(uid);
    }


}
