package net.dunice.basic_server.exception;

import net.dunice.basic_server.constants.ErrorCodes;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(UncorrectedIdException.class)
    public ResponseEntity handle(UncorrectedIdException exception){
        return new ResponseEntity(exception.getMessage(), HttpStatusCode.valueOf(ErrorCodes.TASK_NOT_FOUND.getCode()));
    }
}
