package dev.citobs.citblog2.handler;

import dev.citobs.citblog2.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //모든 익셉션이 발생했을때 연결하게 하는 어노테이션
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public  ResponseDto<String> handleArgumentException(Exception e){
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
