package com.fansos.miconvert.exception;

import com.fansos.miconvert.constant.ResultCodeEnum;

/**
 * @author fzw
 * 秒传失败，需要走正常流程上传
 */
public class FastConvertFalseException extends MiconvertException{

    public FastConvertFalseException() {
        this.message = ResultCodeEnum.FAST_CONVERT_FAIL.getMessage();
        this.code = ResultCodeEnum.FAST_CONVERT_FAIL.getCode();
    }

    public FastConvertFalseException(String message) {
        this.message = message;
        this.code = ResultCodeEnum.FAST_CONVERT_FAIL.getCode();
    }

}
