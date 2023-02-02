package com.fansos.miconvert.exception;

import lombok.Data;
import lombok.Getter;

/**
 * @author fzw
 * 自己定义的异常，响应给前端的http状态都是200
 */
@Getter
public class MiconvertException extends RuntimeException{
    protected String message;
    protected Integer code;
}
