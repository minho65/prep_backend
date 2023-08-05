package com.lgcns.tct_backend.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        log.error("handleHttpRequestMethodNotSupportedException", e);

        final ErrorResponse response = ErrorResponse.create()
                                                    .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                                                    .message(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /*
     * @Valid 검증 실패 시
     */
    @ExceptionHandler(InvalidParameterException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidParameterException(InvalidParameterException e){
        log.error("handleInvalidParameterException", e);

        ErrorCode errorCode = e.getErrorCode();

        ErrorResponse response = ErrorResponse.create()
                                              .status(errorCode.getStatus())
                                              .message(errorCode.getMessage())
                                              .errors(e.getErrors());

        return new ResponseEntity<>(response, HttpStatus.resolve(errorCode.getStatus()));
    }

    /*
     * CustomException을 상속받은 클래스가 예외 발생 시 Catch하여 ErrorResponse 반환
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e){
        log.error("handleCustomException", e);

        ErrorCode errorCode = e.getErrorCode();

        ErrorResponse response = ErrorResponse.create()
                                              .status(errorCode.getStatus())
                                              .code(errorCode.getCode())
                                              .message(errorCode.getMessage());

        return new ResponseEntity<>(response, HttpStatus.resolve(errorCode.getStatus()));
    }

    /*
     * 모든 Exception 핸들링하여 ErrorResponse 반환
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e){
        log.error("Exception", e);

        ErrorResponse response = ErrorResponse.create()
                                              .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                              .message(e.toString());
        
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
