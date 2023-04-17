package net.dunice.basic_server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import net.dunice.basic_server.constants.ValidationConstants;

@Data
public class CreateTodoDto {
    @NotNull(message = ValidationConstants.TODO_TEXT_NOT_NULL)
    @NotBlank(message = ValidationConstants.TODO_TEXT_NOT_NULL)
    @Size(min = 3, max = 160, message = ValidationConstants.TODO_TEXT_SIZE_NOT_VALID)
    private String text;
}
