package net.dunice.basic_server.service;

import java.util.List;

import net.dunice.basic_server.constants.ValidationConstants;
import net.dunice.basic_server.dto.BaseSuccessResponse;
import net.dunice.basic_server.dto.ChangeStatusTodoDto;
import net.dunice.basic_server.dto.ChangeTextTodoDto;
import net.dunice.basic_server.dto.CreateTodoDto;
import net.dunice.basic_server.dto.CustomSuccessResponse;
import net.dunice.basic_server.dto.GetNewsDto;
import net.dunice.basic_server.entity.TaskEntity;
import net.dunice.basic_server.exception.CustomException;
import net.dunice.basic_server.repository.TaskRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
        if (status == null) {
            entities = taskRepo.findAll(PageRequest.of(page, perPage)).getContent();
        }
        else {
            entities = taskRepo.findAllByStatus(status);
        }
        return CustomSuccessResponse.getRequestWithData(GetNewsDto.createNewsDto(entities));
    }

    public BaseSuccessResponse deleteTask(Long id) {
        taskRepo.deleteById(id);
        return BaseSuccessResponse.getOkResponse();
    }

    @Transactional
    public BaseSuccessResponse deleteAllTasks() {
        taskRepo.deleteAllByStatus(true);
        return BaseSuccessResponse.getOkResponse();
    }

    public BaseSuccessResponse updateTaskStatus(Long id, ChangeStatusTodoDto status) {
        taskRepo.save(taskRepo.findById(id)
                              .orElseThrow(() -> new CustomException(ValidationConstants.TASK_NOT_FOUND))
                              .setStatus(status.getStatus())
                     );
        return BaseSuccessResponse.getOkResponse();
    }

    public BaseSuccessResponse updateTaskText(Long id, ChangeTextTodoDto text) {
        taskRepo.save(taskRepo.findById(id)
                              .orElseThrow(() -> new CustomException(ValidationConstants.TASK_NOT_FOUND))
                              .setText(text.getText())
                     );
        return BaseSuccessResponse.getOkResponse();
    }

    @Transactional
    public BaseSuccessResponse updateStatusAllTasks(ChangeStatusTodoDto task) {
        taskRepo.updateAllStatus(task.getStatus());
        return BaseSuccessResponse.getOkResponse();
    }
}
