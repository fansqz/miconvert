package com.fansos.miconvert.exception;

import lombok.Data;
import lombok.Getter;

/**
 * @author fzw
 * @date 2023/02/02 23:13
 * http异常，可以快速返回，被全局捕获并响应给前端
 */
@Getter
public class HttpException extends RuntimeException{

    protected String message;
    protected Integer httpStatusCode = 500;

}
