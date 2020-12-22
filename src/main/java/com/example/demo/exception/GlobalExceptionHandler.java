package com.example.demo.exception;

import com.example.demo.common.CommonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author weiqisheng
 * @Title: GlobalExceptionHandler
 * @ProjectName demo
 * @Description: TODO
 * @date 2020/12/2214:05
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public CommonResult handlerException(ApiException e){
        if (e.getErrorCode() != null) {
            return CommonResult.failed(e.getErrorCode());
        }
        return CommonResult.failed(e.getMessage());
    }
}
