package com.toyproject.hello.dev.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalAccessException(IllegalArgumentException ex) {
        return "error/403";
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFoundException(EntityNotFoundException ex) {
        return "error/404";
    }
    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex) {
        return "error/500";
    }
}

/* ControllerAdvice
*  우선 해당 어노테이션을 사용하기 전에 기존의 프로젝트들은 모두 if,else 또는 try,catch 등을 사용하여 예외 처리를 했었다.
*  이런 경우 제공하는 서비스의 규모가 커질수록 복잡해지고 코드도 길어져서 가독성이 떨어지고 수정하기 매우 힘들었다.
*
*   ControllerAdvice는 여러 컨트롤러에 대해 전역적으로 ExceptionHandler를 적용해준다.
* */