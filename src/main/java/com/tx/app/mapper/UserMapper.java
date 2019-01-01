package com.tx.app.mapper;

import com.tx.app.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  * @ClassName UserMapper
 *  * @Description 用户dao
 *  * @Author Hardy
 *  * @Date 2018年12月06日 11:59
 *  * @Version 1.0.0
 *  
 **/
public interface UserMapper {

    /**
     * 功能描述:
     * 通过用户名查询用户信息
     * @Author: Hardy
     * @Date: 2018年12月06日 13:52:22
     * @param username
     * @return: java.util.List<com.tx.app.entity.User>
     **/
    User getUserByUsername(@Param("username") String username);


    User selectByPrimkey(@Param("uid") Integer uid);


}
