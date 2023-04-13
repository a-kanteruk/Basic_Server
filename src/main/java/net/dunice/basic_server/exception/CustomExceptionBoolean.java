package net.dunice.basic_server.exception;

import lombok.Data;
import net.dunice.basic_server.constants.ErrorCodes;
import org.springframework.http.converter.HttpMessageNotReadableException;

public class CustomExceptionBoolean extends HttpMessageNotReadableException {
        Integer errorCode;
        public CustomExceptionBoolean(String message) {
            super(message);
            this.errorCode = ErrorCodes.getErrorCode(message);
        }
}
