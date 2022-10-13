package com.fansos.miconvert.constant;

public enum ResultCodeEnum {
    /**
     * 响应结果码
     */
    CUSTOM_SIMPLE_ERROR_MESSAGE(1, "自定义错误消息(不需要用户确定,过几秒后消失)"),
    SUCCESS(200, "响应成功"),
    FAIL(201, "失败"),
    ERROR(500, "系统错误");


    private Integer code;
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
