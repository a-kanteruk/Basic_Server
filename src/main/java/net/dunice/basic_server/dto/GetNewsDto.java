package net.dunice.basic_server.dto;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;
import net.dunice.basic_server.entity.TaskEntity;

@Data
@Accessors(chain = true)
public class GetNewsDto {
    List<TaskEntity> content;
    Long notReady;
    Long numberOfElements;
    Long ready;

    public static GetNewsDto createNewsDto(List<TaskEntity> tasks) {
        Long notReady = tasks.stream().filter(x -> !x.getStatus()).count();
        Long ready = tasks.stream().filter(x -> x.getStatus()).count();
        return new GetNewsDto().setContent(tasks)
                                .setNotReady(notReady)
                                .setReady(ready)
                                .setNumberOfElements(tasks.stream().count());
    }
}
