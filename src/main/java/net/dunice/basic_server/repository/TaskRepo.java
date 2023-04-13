package net.dunice.basic_server.repository;

import net.dunice.basic_server.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {

    @Modifying
    @Query("DELETE FROM TaskEntity t WHERE t.status = true")
    void deleteAllReady();
}
