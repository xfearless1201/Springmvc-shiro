package com.tx.app.commons;

/**
 *  * @ClassName ResponseCode
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Hardy
 *  * @Date 2018年12月06日 12:21
 *  * @Version 1.0.0
 *  
 **/
public interface ResponseCode {

    static final int FAIL_STATUS = 2;

    static final int SUCCESS_STATUS = 1;

    static final int ERROR_STATUS = 0;

    static final String SUCCESS_CODE = "SUCCESS";

    static final String ERROR_CODE = "ERROR";

    static final String FAIL_CODE = "FAIL";
}
