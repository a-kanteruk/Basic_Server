package net.dunice.Basic_server.service;

import net.dunice.Basic_server.entity.TaskEntity;
import net.dunice.Basic_server.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;

    public TaskEntity addTask(TaskEntity task){
        return taskRepo.save(task);
    }

    public TaskEntity getTask(Long id){
        return taskRepo.findById(id).get();
    }

    public Long deleteTask(Long id){
        taskRepo.deleteById(id);
        return id;
    }
}
