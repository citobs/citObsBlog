package dev.citobs.citblog2.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //모든 익셉션이 발생했을때 연결하게 하는 어노테이션
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public String handleArgumentException(IllegalArgumentException e){
        return "<h1>"+e.getMessage()+"</h1>";
    }
}
