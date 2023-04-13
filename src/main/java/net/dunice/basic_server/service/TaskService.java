package net.dunice.basic_server.service;

import net.dunice.basic_server.constants.ValidationConstants;
import net.dunice.basic_server.dto.*;
import net.dunice.basic_server.entity.TaskEntity;
import net.dunice.basic_server.exception.CreatePostException;
import net.dunice.basic_server.exception.CustomException;
import net.dunice.basic_server.exception.CustomExceptionBoolean;
import net.dunice.basic_server.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;

    public CustomSuccessResponse  addTask(CreateTodoDto task) throws CreatePostException {
//        if (task.getText().isEmpty()) {throw new CreatePostException(ValidationConstants.TODO_TEXT_NOT_NULL);}

        TaskEntity newTask = TaskEntity.createNewTask(task);
        taskRepo.save(newTask);
        return CustomSuccessResponse.getRequestWithData(newTask);
    }

    public TaskEntity getTask(Long id){
            return taskRepo.findById(id).get();
    }

    public BaseSuccessResponse deleteTask(Long id) throws CustomException {
        taskRepo.findById(id).orElseThrow( () -> new CustomException(ValidationConstants.ID_MUST_BE_POSITIVE));
        taskRepo.deleteById(id);
        return BaseSuccessResponse.getOkResponse();
    }

    @Transactional
    public BaseSuccessResponse deleteAllTasks(){
        taskRepo.deleteAllReady();
        return BaseSuccessResponse.getOkResponse();
    }

    public BaseSuccessResponse updateTaskStatus(Long id, ChangeStatusTodoDto status) throws CustomException, CustomExceptionBoolean {
        taskRepo.findById(id).orElseThrow( () -> new CustomException(ValidationConstants.TASK_NOT_FOUND));
        if (!(status.getStatus() instanceof Boolean)){
            throw new CustomExceptionBoolean(ValidationConstants.HTTP_MESSAGE_NOT_READABLE_EXCEPTION);
        }
        TaskEntity someTask = taskRepo.findById(id).get();
        someTask.setStatus(status.getStatus());
        taskRepo.save(someTask);
        return BaseSuccessResponse.getOkResponse();
    }

    public BaseSuccessResponse updateTaskText(Long id, ChangeTextTodoDto text) throws CustomException {
        taskRepo.findById(id).orElseThrow( () -> new CustomException(ValidationConstants.TASK_NOT_FOUND));
        TaskEntity someTask = taskRepo.findById(id).get();
        someTask.setText(text.getText());
        taskRepo.save(someTask);
        return BaseSuccessResponse.getOkResponse();
    }

    public BaseSuccessResponse updateStatusAllTasks(ChangeStatusTodoDto task){
            for (TaskEntity item: taskRepo.findAll()){
                item.setStatus(task.getStatus());
                taskRepo.save(item);
            }
            return BaseSuccessResponse.getOkResponse();
    }
}
