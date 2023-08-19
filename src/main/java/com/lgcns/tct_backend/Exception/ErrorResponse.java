package com.lgcns.tct_backend.Exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

/*
 * Issue : Jackson is not properly deserializing the ErrorResponse object
 * Solved : add @Getter
 */
@Getter
public class ErrorResponse {
    private LocalDateTime timestamp = LocalDateTime.now();

    private String message; // 예외 메시지
    private String code; // 예외 세분 코드, 사용자 지정
    private int status; // HTTP 상태 값 저장

    /* 
     *  @Valid Parameter 검증 통과하지 못한 필드가 담김.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("error")
    private List<CustomFieldError> customFieldErrors;

    public ErrorResponse() {

    }

    static public ErrorResponse create(){
        return new ErrorResponse();
    }

    public ErrorResponse code(String code) {
        this.code = code;
        return this;
    }

    public ErrorResponse status(int status){
        this.status = status;
        return this;
    }

    public ErrorResponse message(String message){
        this.message = message;
        return this;
    }

    public ErrorResponse errors(Errors errors){
        setCustomFieldErrors(errors.getFieldErrors());
        return this;
    }

    /* 
     * BindingResult.getFieldErrors() 메소드를 통해 전달받은 fieldErrors
     */
    public void setCustomFieldErrors(List<FieldError> fieldErrors){
        this.customFieldErrors = new ArrayList<>();

        fieldErrors.forEach(error -> {
            customFieldErrors.add(CustomFieldError.builder()
                .field(error.getCodes()[0])
                .value(error.getRejectedValue())
                .reason(error.getDefaultMessage())
                .build()
            );
        });
    }

    /*
     * Parameter 검증에 통과하지 못한 필드가 담긴 클래스
     */
    public static class CustomFieldError {
        private String field;
        private Object value;
        private String reason;

        @Builder
        public CustomFieldError(String field, Object value, String reason){
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public String getField(){
            return this.field;
        }

        public Object getValue(){
            return this.value;
        }

        public String getReason(){
            return reason;
        }
    }
}