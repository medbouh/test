package com.a2a.api.microservices;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException  {

    private HttpStatus httpStatus;

    private String code ;

    private String message ;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiException (String message){
        super(message);
    }
}
