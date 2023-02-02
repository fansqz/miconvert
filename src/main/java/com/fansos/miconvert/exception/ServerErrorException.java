package com.fansos.miconvert.exception;

public class ServerErrorException extends HttpException{

    public ServerErrorException(String message) {
        this.message = message;
        this.httpStatusCode = 500;
    }

}
