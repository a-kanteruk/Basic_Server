package net.dunice.basic_server.service;

import net.dunice.basic_server.constants.ValidationConstants;
import net.dunice.basic_server.dto.*;
import net.dunice.basic_server.entity.TaskEntity;
import net.dunice.basic_server.exception.CustomException;
import net.dunice.basic_server.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;

    public CustomSuccessResponse addTask(CreateTodoDto task) {
        TaskEntity newTask = TaskEntity.createNewTask(task);
        taskRepo.save(newTask);
        return CustomSuccessResponse.getRequestWithData(newTask);
    }

    public CustomSuccessResponse getTaskPaginate(int page, int perPage, Boolean status) {
        List<TaskEntity> entities;

        if (status == null){
            entities = taskRepo.findAll(PageRequest.of(page, perPage)).getContent();
        } else {
            entities = taskRepo.findAllByStatus(status);
        }
        return CustomSuccessResponse.getRequestWithData(GetNewsDto.CreateNewsDto(entities));
    }

    public BaseSuccessResponse deleteTask(Long id) throws CustomException {
        taskRepo.findById(id).orElseThrow( () -> new CustomException(ValidationConstants.ID_MUST_BE_POSITIVE));
        taskRepo.deleteById(id);
        return BaseSuccessResponse.getOkResponse();
    }

    @Transactional
    public BaseSuccessResponse deleteAllTasks() {
        taskRepo.deleteAllByStatus(true);
        return BaseSuccessResponse.getOkResponse();
    }

    public BaseSuccessResponse updateTaskStatus(Long id, ChangeStatusTodoDto status) throws CustomException {
        taskRepo.findById(id).orElseThrow( () -> new CustomException(ValidationConstants.TASK_NOT_FOUND));
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

    public BaseSuccessResponse updateStatusAllTasks(ChangeStatusTodoDto task) {
            for (TaskEntity item: taskRepo.findAll()){
                item.setStatus(task.getStatus());
                taskRepo.save(item);
            }
            return BaseSuccessResponse.getOkResponse();
    }
}
