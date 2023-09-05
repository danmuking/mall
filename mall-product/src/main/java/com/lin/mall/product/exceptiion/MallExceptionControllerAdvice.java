package com.lin.mall.product.exceptiion;

import com.lin.common.exception.BizCodeEnum;
import com.lin.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @projectName: mall
 * @package: com.lin.mall.product.exceptiion
 * @className: MallExceptionContrillerAdvice
 * @author: LinYi
 * @description: TODO
 * @date: 2023/9/5 16:18
 * @version: 1.0
 */

@Slf4j
@RestControllerAdvice(basePackages = "com.lin.mall.product.controller")
public class MallExceptionControllerAdvice {

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public R handleVaildException(MethodArgumentNotValidException e){
        log.error("数据校验出现问题{}，异常类型{}",e.getMessage(),e.getClass());
        BindingResult bindResult = e.getBindingResult();

        Map<String, String> errorMap = new HashMap<>(4);
        bindResult.getFieldErrors().forEach(
                fieldError -> {
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
        );
        return R.error(BizCodeEnum.VALID_EXCEPTION.getCode(), BizCodeEnum.VALID_EXCEPTION.getMsg()).put("data",errorMap);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){
        log.error("错误：",throwable);
        return R.error(BizCodeEnum.UNKNOW_EXCEPTION.getCode(),BizCodeEnum.UNKNOW_EXCEPTION.getMsg());
    }

}
