package net.dunice.Basic_server.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangeStatusTodoDto {
    @NotNull
    private Boolean status;
}
