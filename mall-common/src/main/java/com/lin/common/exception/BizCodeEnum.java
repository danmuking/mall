package com.lin.common.exception;

/**
 * @projectName: mall
 * @package: com.lin.common.exception
 * @className: BizCodeEnume
 * @author: LinYi
 * @description: 错误码和错误信息定义类
 * @date: 2023/9/5 16:10
 * @version: 1.0
 */
public enum BizCodeEnum {
    UNKNOW_EXCEPTION(10000,"系统未知异常"),
    VALID_EXCEPTION(10001,"参数格式校验失败");

    private int code;
    private String msg;
    BizCodeEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }
}
