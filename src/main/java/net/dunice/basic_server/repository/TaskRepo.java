package net.dunice.basic_server.repository;

import java.util.List;

import net.dunice.basic_server.entity.TaskEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {

    void deleteAllByStatus(Boolean status);

    Page<TaskEntity> findAll(Pageable pageable);

    List<TaskEntity> findAllByStatus(Boolean status);

    @Modifying
    @Query("UPDATE TaskEntity t SET t.status = :status")
    void updateAllStatus(@Param("status") Boolean status);
}
