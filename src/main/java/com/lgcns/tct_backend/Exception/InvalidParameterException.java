package com.lgcns.tct_backend.Exception;

import org.springframework.validation.Errors;

public class InvalidParameterException extends CustomException {
    private static final long serialVersionUID  = -2116671122895194101L;

    private final Errors errors;
    
    public InvalidParameterException(Errors errors){
        super(ErrorCode.INVALID_PARAMETER); // enum 클래스 활용법 : 선택한 ErrorCode 객체를 넘겨준다
        this.errors = errors;
    }

    public Errors getErrors() {
        return this.errors;
    }
}
