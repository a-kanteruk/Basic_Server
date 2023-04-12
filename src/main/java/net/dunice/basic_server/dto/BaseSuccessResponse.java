package net.dunice.basic_server.dto;

import lombok.Data;

@Data
public class BaseSuccessResponse {
    private int statusCode = 0;
    private boolean success = true;
}
