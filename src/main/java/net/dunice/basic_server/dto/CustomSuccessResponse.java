package net.dunice.basic_server.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomSuccessResponse<T> {
    T data;
    int statusCode;
    boolean success;

    public static <T> CustomSuccessResponse getRequestWithData (T data){

        return new CustomSuccessResponse<>()
                .setData(data)
                .setStatusCode(1)
                .setSuccess(true);
    }
}
