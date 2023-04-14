package net.dunice.basic_server.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import net.dunice.basic_server.entity.TaskEntity;

import java.util.List;

@Data
@Accessors(chain = true)
public class GetNewsDto {
    List<TaskEntity> content;
    Long notReady;
    Long numberOfElements;
    Long ready;

    public static GetNewsDto CreateNewsDto(List<TaskEntity> tasks){
        Long notReady = tasks.stream().filter(x -> x.getStatus() == false).count();
        Long ready = tasks.stream().filter(x -> x.getStatus() == true).count();
        return new GetNewsDto().setContent(tasks)
                                .setNotReady(notReady)
                                .setReady(ready)
                                .setNumberOfElements(tasks.stream().count());
    }
}
