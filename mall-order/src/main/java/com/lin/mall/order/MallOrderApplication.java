package com.lin.mall.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @projectName: mall
 * @package: com.lin.mall.order
 * @className: MallOrderApplication
 * @author: LinYi
 * @description: TODO
 * @date: 2023/8/26 19:04
 * @version: 1.0
 */

@MapperScan("com.lin.mall.order.dao")
@SpringBootApplication
public class MallOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallOrderApplication.class, args);
    }
}