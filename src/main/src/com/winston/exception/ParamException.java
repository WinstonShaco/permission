package com.winston.exception;

/**
 * @Author: 于新泽
 * @Date: Created in 23:45 2018/9/12.
 * @site :
 */

public class ParamException extends RuntimeException{
    public ParamException() {
        super();
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamException(Throwable cause) {
        super(cause);
    }

    protected ParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
