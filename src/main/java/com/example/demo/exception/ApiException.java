package com.example.demo.exception;

import com.example.demo.common.IErrorCode;

/**
 * @author weiqisheng
 * @Title: ApiException
 * @ProjectName demo
 * @Description: TODO
 * @date 2020/12/2214:05
 */
public class ApiException extends RuntimeException{

    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
