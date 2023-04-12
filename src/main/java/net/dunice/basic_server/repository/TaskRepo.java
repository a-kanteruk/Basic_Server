package net.dunice.basic_server.repository;

import net.dunice.basic_server.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<TaskEntity, Long> {

}
