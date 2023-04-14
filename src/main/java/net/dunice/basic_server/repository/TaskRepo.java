package net.dunice.basic_server.repository;

import net.dunice.basic_server.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {

//    @Modifying
//    @Query("DELETE FROM TaskEntity t WHERE t.status = true")
//    void deleteAllReady();

    void deleteAllByStatus(Boolean status);

    Page<TaskEntity> findAll(Pageable pageable);

    List<TaskEntity> findAllByStatus(Boolean status);
}
