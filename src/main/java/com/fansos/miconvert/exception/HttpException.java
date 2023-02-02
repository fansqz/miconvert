package com.fansos.miconvert.exception;

/**
 * @author fzw
 * @date 2023/02/02 23:13
 * 自定义的http异常，可以快速返回，被全局捕获并响应给前端
 */
public class HttpException extends RuntimeException{

    protected String message;
    protected Integer httpStatusCode = 500;

}
