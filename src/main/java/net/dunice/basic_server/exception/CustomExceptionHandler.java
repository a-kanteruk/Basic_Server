package net.dunice.basic_server.exception;

import net.dunice.basic_server.constants.ErrorCodes;
import net.dunice.basic_server.dto.BaseSuccessResponse;
import net.dunice.basic_server.dto.CustomSuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity handle(CustomException exception){
        return new ResponseEntity(BaseSuccessResponse.getBadResponse(exception.getErrorCode()),
                                                                        HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle(MethodArgumentNotValidException mavex){
        List<Integer> codes = mavex
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(e -> ErrorCodes.getErrorCode(e.getDefaultMessage()))
                .toList();
        return new ResponseEntity(CustomSuccessResponse.getBadCustomResponse(codes.get(0), codes),
                                                                        HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity handle(CustomException exception){
        return new ResponseEntity(BaseSuccessResponse.getBadResponse(exception.getErrorCode()),
                HttpStatus.BAD_REQUEST);
    }
}
