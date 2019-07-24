package com.a2a.api.microservices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@EnableWebMvc
@Controller
@ControllerAdvice("com.a2a.api.microservices")
public class HandlerException extends ResponseEntityExceptionHandler {

        @ExceptionHandler(ApiException.class)
        public final ResponseEntity<ApiError> handleNotFoundException(ApiException ex, WebRequest request) {
            ApiError apiError = new ApiError("BAD_REQUEST",ex.getMessage());
            return new ResponseEntity<ApiError>(apiError, HttpStatus.NOT_FOUND);
        }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiError> handleException(ApiException ex, WebRequest request) {
        ApiError apiError = new ApiError("INTERNAL_ERROR","En raison d'un probème technique le service est momentanément indisponible");
        return new ResponseEntity<ApiError>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
