package com.lgcns.tct_backend.Exception;

public class CustomException extends RuntimeException {
    
    /*
     * Serialize 인터페이스 구현 시 serialVersionUID 값을 할당하는 이유
     * Serializable을 상속하는 Class의 경우 Class의 versioning 용도로 serialVersionUID 변수를 사용.
     * 이때, serialVersionUID 값을 명시적으로 지정하지 않으면 Compiler가 계산한 값을 부여하는 데,
     * Serizable Class 또는 Outer Class에 변경이 있으면 serialVersionUID 값이 바뀌게 된다.
     * 이는 Serialize 또는 Deserialize 할때 serialVersionUID 값이 다르면 
     * InvalidClassException이 발생하여 저장된 값을 가져오지 못하는 문제가 나타날 수 있다.
     * 
     * 참고)
     *      - https://m.blog.naver.com/writer0713/220922099055
     *      - https://blog.naver.com/kkson50/220564273220
     */
    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;

    public CustomException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
