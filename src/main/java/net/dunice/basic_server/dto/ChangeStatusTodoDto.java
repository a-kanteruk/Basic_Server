package net.dunice.basic_server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.BooleanFlag;
import lombok.Data;
import net.dunice.basic_server.constants.ValidationConstants;

@Data
public class ChangeStatusTodoDto {
    @NotNull(message = ValidationConstants.TODO_STATUS_NOT_NULL)
    private Boolean status;
}
