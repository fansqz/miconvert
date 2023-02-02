package com.fansos.miconvert.handler;

import com.fansos.miconvert.exception.HttpException;
import com.fansos.miconvert.exception.MiconvertException;
import com.fansos.miconvert.model.result.Result;
import com.fansos.miconvert.constant.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(MiconvertException.class)
    public Result<?> miconvertExceptionHandler(MiconvertException miconvertException) {
        return Result.build(miconvertException.getCode(), miconvertException.getMessage());
    }

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<?> httpExceptionHandler(HttpException httpException) {
        log.error("请求出现错误：{},{}",httpException.getClass().getName(), httpException.getLocalizedMessage());
        return ResponseEntity.status(httpException.getHttpStatusCode())
                .body(Result.build(httpException.getHttpStatusCode(), httpException.getMessage()));
    }

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
