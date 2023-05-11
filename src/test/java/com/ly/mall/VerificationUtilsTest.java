package com.ly.mall;

import com.ly.mall.utils.VerificationUtils;
import org.junit.Test;

/**
 * @projectName: mall
 * @package: com.ly.mall
 * @className: VerificationUtilsTest
 * @author: LinYi
 * @description: VerificationUtils测试类
 * @date: 2023/5/11 22:17
 * @version: 1.0
 */
public class VerificationUtilsTest {
    @Test
    public void testVerificationUtils() throws Exception {
        for(int i=0;i<1000;i++){
            String code = VerificationUtils.generateVerificationCode();
            System.out.println(code);
        }
    }
}
