package net.dunice.Basic_server.repository;

import net.dunice.Basic_server.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<TaskEntity, Long> {

}
