package com.lin.mall.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @projectName: mall
 * @package: PACKAGE_NAME
 * @className: com.lin.mall.member.MallMemberApplication
 * @author: LinYi
 * @description: TODO
 * @date: 2023/8/26 18:58
 * @version: 1.0
 */

@MapperScan("com.lin.mall.member.dao")
@SpringBootApplication
public class MallMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallMemberApplication.class, args);
    }
}
