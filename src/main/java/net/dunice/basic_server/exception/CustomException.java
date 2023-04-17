package net.dunice.basic_server.exception;

import lombok.Data;
import net.dunice.basic_server.constants.ErrorCodes;

@Data
public class CustomException extends RuntimeException {
    final Integer errorCode;
    public CustomException(String message) {
        super(message);
        this.errorCode = ErrorCodes.getErrorCode(message);
    }
}
