package com.ly.mall.utils;

import java.util.Random;

/**
 * @projectName: mall
 * @package: com.ly.mall.utils
 * @className: VerificationUtils
 * @author: LinYi
 * @description: 验证相关工具类
 * @date: 2023/5/11 22:08
 * @version: 1.0
 */
public class VerificationUtils {
    /**
     * @param :
     * @return String
     * @author LinYi
     * @description 生成6位随机数字验证码
     * @date 2023/5/11 22:09
     */
    public static String generateVerificationCode(){
        Random random = new Random();
        int num = random.nextInt(1000000);
        StringBuilder verification = new StringBuilder(String.valueOf(num));
        while (verification.length() < 6){
            verification.insert(0, "0");
        }
        return verification.toString();
    }
}
