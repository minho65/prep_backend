package com.lgcns.tct_backend.Exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // Runtime Error
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST.value(), null, "Invalid Request Parameter"),

    // Business Error
    INVALID_USER_ID(HttpStatus.BAD_REQUEST.value(), null, "Invalid userId"),
    INVALID_LIST_ID(HttpStatus.BAD_REQUEST.value(), null, "Invalid listId"),
    INVALID_REQUEST_PROVIDED(HttpStatus.BAD_REQUEST.value(), null, "invalid request provided");

    private final String code;
    private final String message;
    private final int status;

    public String getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }

    public int getStatus(){
        return this.status;
    }

    ErrorCode(final int status, final String code, final String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
