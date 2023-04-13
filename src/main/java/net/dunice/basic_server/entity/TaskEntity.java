package net.dunice.basic_server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.experimental.Accessors;
import net.dunice.basic_server.constants.ValidationConstants;
import net.dunice.basic_server.dto.CreateTodoDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Accessors(chain = true)
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(message = ValidationConstants.ID_MUST_BE_POSITIVE)
    private Long id;
    private String text;
    private Boolean status = false;
    @CreationTimestamp
    private LocalDateTime creationTime;
    @UpdateTimestamp
    private LocalDateTime updateTime;

    public static TaskEntity createNewTask(CreateTodoDto task){
        return new TaskEntity().setText(task.getText());
    }
}
