package com.fansos.miconvert.exception;

public class ParameterException extends HttpException{

    public ParameterException(String message) {
        this.message = message;
        this.httpStatusCode = 400;
    }

}
