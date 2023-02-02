package com.fansos.miconvert.exception;

import com.fansos.miconvert.constant.ResultCodeEnum;

public class FastConvertFalseException extends MiconvertException{

    public FastConvertFalseException() {
        this.message = ResultCodeEnum.FAST_CONVERT_FALSE.getMessage();
        this.code = ResultCodeEnum.FAST_CONVERT_FALSE.getCode();
    }

    public FastConvertFalseException(String message) {
        this.message = message;
        this.code = ResultCodeEnum.FAST_CONVERT_FALSE.getCode();
    }

}
