package com.ly.mall.service;



/**
 * @author LinYi
 * @description redisService接口
 * @date 2023/5/9 22:53
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public interface RedisService {
    /**
     * @param key: 键
     * @param value: 值
     * @param time: 过期时间
     * @author LinYi
     * @description 将键值对添加至redis
     * @date 2023/5/9 22:55
     */
    void set(String key,Object value,long time);

    /**
     * @param key: 键
     * @param value: 值
     * @author LinYi
     * @description 将键值对添加至redis
     * @date 2023/5/9 22:55
     */
    void set(String key,Object value);

    /**
     * @param key: 键
     * @return Object 值
     * @author LinYi
     * @description 获取对应值
     * @date 2023/5/9 22:58
     */
    Object get(String key);

    /**
     * @param key: 键
     * @return Boolean 是否删除陈宫
     * @author LinYi
     * @description 删除键值对
     * @date 2023/5/9 22:59
     */
    Boolean del(String key);


    /**
     * @param key: 键
     * @return Boolean 是否有该属性
     * @author LinYi
     * @description 判断是否有该属性
     * @date 2023/5/9 22:59
     */
    Boolean hasKey(String key);

}
