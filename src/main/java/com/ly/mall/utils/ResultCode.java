package com.ly.mall.utils;

/**
 * @author LinYi
 * @description  返回状态码封装类
 */
public enum ResultCode implements IErrorCode{
    SUCCESS(200,"操作成功"),
    FAILED(500,"操作失败"),
    VALIDATE_FAILED(404,"参数校验失败"),
    UNAUTHORIZED(401,"未登录或token过期"),
    FORBIDDEN(403,"没有相关权限");

    private long code;
    private String msg;

    private ResultCode(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public long getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
