package com.ly.mall.utils;

/**
 * @author LinYi
 * @description api返回码接口
 */
public interface IErrorCode {
    /**
     * 获得状态码
     * @return 状态码
     */
    long getCode();

    /**
     * 获得请求状态信息
     * @return 请求信息
     */
    String getMsg();
}
