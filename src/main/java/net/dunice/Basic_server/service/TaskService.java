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

    public String deleteAllTasks(){
        taskRepo.deleteAll();
        return "All tasks are removed.";
    }

    public TaskEntity updateTaskStatus(Long id, TaskEntity task){
        if (taskRepo.findById(id) == null){
            return null;
        }
        TaskEntity someTask = taskRepo.findById(id).get();
        someTask.setTaskStatus(task.getTaskStatus());
        taskRepo.save(someTask);
        return someTask;
    }

    public TaskEntity updateTaskText(Long id, TaskEntity task){
        if (taskRepo.findById(id) == null){
            return null;
        }
        TaskEntity someTask = taskRepo.findById(id).get();
        someTask.setText(task.getText());
        taskRepo.save(someTask);
        return someTask;
    }

    public String updateStatusAllTasks(TaskEntity task){
        for (TaskEntity item: taskRepo.findAll()){
            item.setTaskStatus(task.getTaskStatus());
            taskRepo.save(item);
        }
        return "All tasks changed.";
    }
}
