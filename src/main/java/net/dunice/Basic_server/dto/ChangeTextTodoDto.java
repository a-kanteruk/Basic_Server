package net.dunice.Basic_server.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangeTextTodoDto {
    @Size(min = 3, max = 160)
    String text;
}
