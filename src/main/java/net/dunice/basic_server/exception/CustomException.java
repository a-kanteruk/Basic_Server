package net.dunice.basic_server.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
