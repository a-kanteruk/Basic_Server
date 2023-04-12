package net.dunice.basic_server.service;

import net.dunice.basic_server.constants.ErrorCodes;
import net.dunice.basic_server.constants.ValidationConstants;
import net.dunice.basic_server.dto.*;
import net.dunice.basic_server.entity.TaskEntity;
import net.dunice.basic_server.exception.UncorrectedIdException;
import net.dunice.basic_server.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;

    public CustomSuccessResponse  addTask(CreateTodoDto task){
        TaskEntity newTask = new TaskEntity();
        newTask.setText(task.getText());
        taskRepo.save(newTask);
        return CustomSuccessResponse.getRequestWithData(newTask);
    }

    public TaskEntity getTask(Long id){
            return taskRepo.findById(id).get();
    }

    public BaseSuccessResponse deleteTask(Long id) throws UncorrectedIdException {
        if (taskRepo.findById(id) == null){
            throw new UncorrectedIdException(ValidationConstants.TASK_NOT_FOUND);
        }
        taskRepo.deleteById(id);
        return new BaseSuccessResponse();
    }

    public BaseSuccessResponse deleteAllTasks(){
        taskRepo.deleteAll();
        return new BaseSuccessResponse();
    }

    public BaseSuccessResponse updateTaskStatus(Long id, ChangeStatusTodoDto task) throws UncorrectedIdException {
        if (taskRepo.findById(id) == null){
            throw new UncorrectedIdException(ValidationConstants.TASK_NOT_FOUND);
        }
        TaskEntity someTask = taskRepo.findById(id).get();
        someTask.setTaskStatus(task.getStatus());
        taskRepo.save(someTask);
        return new BaseSuccessResponse();
    }

    public BaseSuccessResponse updateTaskText(Long id, ChangeTextTodoDto text) throws UncorrectedIdException {
        if (taskRepo.findById(id) == null){
                throw new UncorrectedIdException(ValidationConstants.TASK_NOT_FOUND);
        }
        TaskEntity someTask = taskRepo.findById(id).get();
        someTask.setText(text.getText());
        taskRepo.save(someTask);
        return new BaseSuccessResponse();
    }

    public BaseSuccessResponse updateStatusAllTasks(ChangeStatusTodoDto task){
            for (TaskEntity item: taskRepo.findAll()){
                item.setTaskStatus(task.getStatus());
                taskRepo.save(item);
            }
            return new BaseSuccessResponse();
    }
}
