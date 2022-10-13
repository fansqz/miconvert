package com.fansos.miconvert.handler;

import com.fansos.miconvert.model.result.Result;
import com.fansos.miconvert.constant.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author fzw
 * 异常全局处理
 * @date 2022-10-13 17:24
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<?> exceptionHandler(Exception exception) {
        log.error("未知错误：{},{}", exception.getClass().getName(), exception.getMessage());
        exception.printStackTrace();
        return Result.build(ResultCodeEnum.ERROR.getCode(), ResultCodeEnum.ERROR.getMessage());
    }


    @ExceptionHandler(RuntimeException.class)
    public Result<?> runtimeExceptionHandler(RuntimeException exception) {
        log.error("运行时错误：{},{}", exception.getClass().getName(), exception.getLocalizedMessage());
        return Result.build(ResultCodeEnum.ERROR.getCode(), exception.getMessage());
    }


    @InitBinder
    public void handleException (WebDataBinder binder){
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }
}
