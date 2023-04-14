package net.dunice.basic_server.exception;

import java.util.List;
import java.util.stream.Collectors;


import jakarta.validation.ConstraintViolationException;
import net.dunice.basic_server.constants.ErrorCodes;
import net.dunice.basic_server.constants.ValidationConstants;
import net.dunice.basic_server.dto.BaseSuccessResponse;
import net.dunice.basic_server.dto.CustomSuccessResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity handle(CustomException exception) {
        return new ResponseEntity(BaseSuccessResponse.getBadResponse(exception.getErrorCode()),
                                                                        HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle(MethodArgumentNotValidException mavex) {
        List<Integer> codes = mavex
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(e -> ErrorCodes.getErrorCode(e.getDefaultMessage()))
                .toList();
        return new ResponseEntity(CustomSuccessResponse.getBadCustomResponse(codes.get(0), codes),
                                                                        HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handle(HttpMessageNotReadableException exception) {
        String message = ValidationConstants.HTTP_MESSAGE_NOT_READABLE_EXCEPTION;
        return new ResponseEntity(BaseSuccessResponse.getBadResponse(ErrorCodes.getErrorCode(message)),
                                                                    HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handle(ConstraintViolationException exception) {
        List<Integer> codes = exception.getConstraintViolations()
                                        .stream()
                                        .map(x -> ErrorCodes.getErrorCode(x.getMessage()))
                                        .collect(Collectors.toList());
        return new ResponseEntity(CustomSuccessResponse.getBadCustomResponse(codes.get(0), codes),
                HttpStatus.BAD_REQUEST);
    }

}
