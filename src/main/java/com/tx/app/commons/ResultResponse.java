package com.tx.app.commons;

/**
 *  *  @ClassName ResultResponse
 *  *  @Description 封装返回类
 *  *  @Author Hardy
 *  *  @Date 2018年12月06日 12:19
 *  *  @Version 1.0.0
 *  
 **/
public class ResultResponse {

    private int status;

    private String code;

    private String message;

    private Object data;

    public ResultResponse(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public ResultResponse(int status, String code, String message, Object data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultResponse success(String message){
        return new ResultResponse(ResponseCode.SUCCESS_STATUS,ResponseCode.SUCCESS_CODE,message);
    }

    public static ResultResponse success(String message,Object data){
        return new ResultResponse(ResponseCode.SUCCESS_STATUS,ResponseCode.SUCCESS_CODE,message,data);
    }

    public static ResultResponse fail(String message){
        return new ResultResponse(ResponseCode.FAIL_STATUS,ResponseCode.FAIL_CODE,message);
    }

    public static ResultResponse fail(String message,Object data){
        return new ResultResponse(ResponseCode.FAIL_STATUS,ResponseCode.FAIL_CODE,message,data);
    }

    public static ResultResponse error(String message){
        return new ResultResponse(ResponseCode.ERROR_STATUS,ResponseCode.ERROR_CODE,message);
    }

    public static ResultResponse error(String message,Object data){
        return new ResultResponse(ResponseCode.ERROR_STATUS,ResponseCode.ERROR_CODE,message,data);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
