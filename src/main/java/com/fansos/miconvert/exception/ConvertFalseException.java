package com.fansos.miconvert.exception;

import com.fansos.miconvert.constant.ResultCodeEnum;

/**
 * @author fzw
 * 文件解析失败
 */
public class ConvertFalseException extends MiconvertException{

    public ConvertFalseException(String message) {
        this.message = message;
        this.code = ResultCodeEnum.CONVERT_FAIL.getCode();
    }

    public ConvertFalseException() {
        this.message = ResultCodeEnum.CONVERT_FAIL.getMessage();
        this.code = ResultCodeEnum.CONVERT_FAIL.getCode();
    }
}
