package net.dunice.Basic_server.service;

import net.dunice.Basic_server.dto.ChangeStatusTodoDto;
import net.dunice.Basic_server.dto.ChangeTextTodoDto;
import net.dunice.Basic_server.dto.CreateTodoDto;
import net.dunice.Basic_server.entity.TaskEntity;
import net.dunice.Basic_server.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;

    public TaskEntity addTask(CreateTodoDto task){
        TaskEntity newTask = new TaskEntity();
        newTask.setText(task.getText());
        return taskRepo.save(newTask);
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

    public TaskEntity updateTaskStatus(Long id, ChangeStatusTodoDto task){
        if (taskRepo.findById(id) == null){
            return null;
        }
        TaskEntity someTask = taskRepo.findById(id).get();
        someTask.setTaskStatus(task.getStatus());
        taskRepo.save(someTask);
        return someTask;
    }

    public TaskEntity updateTaskText(Long id, ChangeTextTodoDto text){
        if (taskRepo.findById(id) == null){
            return null;
        }
        TaskEntity someTask = taskRepo.findById(id).get();
        someTask.setText(text.getText());
        taskRepo.save(someTask);
        return someTask;
    }

    public String updateStatusAllTasks(ChangeStatusTodoDto task){
        for (TaskEntity item: taskRepo.findAll()){
            item.setTaskStatus(task.getStatus());
            taskRepo.save(item);
        }
        return "All tasks changed.";
    }
}
