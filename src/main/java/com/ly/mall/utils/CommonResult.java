package com.ly.mall.utils;

import lombok.Data;

/**
 * @author LinYi
 * @description 通用的返回结果封装类
 */
@Data
public class CommonResult<T> {
    /**
     * 状态码
     */
    private long code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 数据封装
     */
    private T data;

    protected CommonResult(){
    }

    protected CommonResult(long code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 操作成功返回结果
     * @param data 获取的数据
     * @return CommonResult
     * @param <T> 获取数据的泛型
     */
    public static <T> CommonResult<T> success(T data){
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),data);
    }

    /**
     * 操作成功返回结果
     * @param data 获取的数据
     * @param msg 提示信息
     * @return CommonResult
     * @param <T> 获取数据的泛型
     */
    public static <T> CommonResult<T> success(T data,String msg){
        return new CommonResult<>(ResultCode.SUCCESS.getCode(),msg,data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @return CommonResult
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode){
        return new CommonResult<T>(errorCode.getCode(),errorCode.getMsg(),null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param msg 提示信息
     * @return CommonResult
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode,String msg){
        return new CommonResult<>(errorCode.getCode(),msg,null);
    }

    /**
     * 失败返回结果
     * @param msg 提示信息
     * @return CommonResult
     */
    public static <T> CommonResult<T> failed(String msg){
        return new CommonResult<>(ResultCode.FAILED.getCode(), msg, null);
    }

    /**
     * 失败返回结果
     * @return CommonResult
     */
    public static <T> CommonResult<T> failed(){
        return failed(ResultCode.FAILED.getMsg());
    }

    /**
     * 参数校验失败返回结果
     * @return CommonResult
     */
    public static <T> CommonResult<T> validate_failed(){
        return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(),ResultCode.VALIDATE_FAILED.getMsg(),null);
    }

    /**
     * 参数校验失败返回结果
     * @return CommonResult
     */
    public static <T> CommonResult<T> validate_failed(String msg){
        return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(),msg,null);
    }


}
