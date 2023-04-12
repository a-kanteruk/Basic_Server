package net.dunice.basic_server.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
import net.dunice.basic_server.constants.ErrorCodes;
import net.dunice.basic_server.constants.ValidationConstants;

@Data
public class ChangeTextTodoDto {
    @Size(min = 3, max = 160, message = ValidationConstants.TODO_TEXT_SIZE_NOT_VALID)
    String text;
}
