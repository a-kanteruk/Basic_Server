package net.dunice.basic_server.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import net.dunice.basic_server.constants.ValidationConstants;

@Data
public class ChangeStatusTodoDto {
    @NotNull(message = ValidationConstants.TODO_TEXT_NOT_NULL)
    private Boolean status;
}
