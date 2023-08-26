package com.lin.mall.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @projectName: mall
 * @package: com.lin.mall
 * @className: MallWareApplication
 * @author: LinYi
 * @description: TODO
 * @date: 2023/8/26 19:09
 * @version: 1.0
 */

@MapperScan("com.lin.mall.ware.dao")
@SpringBootApplication
public class MallWareApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallWareApplication.class, args);
    }
}