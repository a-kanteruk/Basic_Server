package net.dunice.basic_server.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CustomSuccessResponse<T> {
    T data;
    int statusCode;
    boolean success;
    List<Integer> codes;

    public static <T> CustomSuccessResponse getRequestWithData (T data){
        return new CustomSuccessResponse<>()
                .setData(data)
                .setStatusCode(1)
                .setSuccess(true);
    }

    public static CustomSuccessResponse getBadCustomResponse(int statusCode, List<Integer> codes){
        return new CustomSuccessResponse<>()
                .setStatusCode(statusCode)
                .setSuccess(true)
                .setCodes(codes);
    }

}
