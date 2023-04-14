package net.dunice.basic_server.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import net.dunice.basic_server.constants.ValidationConstants;
import net.dunice.basic_server.dto.*;
import net.dunice.basic_server.exception.CustomException;
import net.dunice.basic_server.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/v1/todo")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addTask(@RequestBody @Valid CreateTodoDto task) {
            return ResponseEntity.ok(taskService.addTask(task));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTaskPaginate(
            @RequestParam
            @NotNull(message = ValidationConstants.PARAM_PAGE_NOT_NULL)
            @Positive(message = ValidationConstants.TASKS_PAGE_GREATER_OR_EQUAL_1)
            @Max(message = ValidationConstants.TASKS_PAGE_GREATER_OR_EQUAL_1, value = Integer.MAX_VALUE)
            @Min(message = ValidationConstants.TASKS_PAGE_GREATER_OR_EQUAL_1, value = 1)
            Integer page,
            @RequestParam
            @NotNull(message = ValidationConstants.PARAM_PER_PAGE_NOT_NULL)
            @Positive(message = ValidationConstants.TASKS_PER_PAGE_GREATER_OR_EQUAL_1)
            @Max(message = ValidationConstants.TASKS_PER_PAGE_LESS_OR_EQUAL_100, value = 100)
            @Min(message = ValidationConstants.TASKS_PER_PAGE_GREATER_OR_EQUAL_1, value = 1)
            Integer perPage, Boolean status){
            return ResponseEntity.ok(taskService.getTaskPaginate(page, perPage, status));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteTask(@Valid @PathVariable @Positive(message = ValidationConstants
                                                                    .ID_MUST_BE_POSITIVE) Long id)
                                                                                        throws CustomException {
            return ResponseEntity.ok(taskService.deleteTask(id));
    }

    @DeleteMapping
    public ResponseEntity deleteAllTasks(){
            return ResponseEntity.ok(taskService.deleteAllTasks());
    }

    @PatchMapping(value = "/status/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateTaskStatus(@PathVariable Long id,
                                           @RequestBody @Valid ChangeStatusTodoDto newStatus)
                                            throws CustomException {

            return ResponseEntity.ok(taskService.updateTaskStatus(id, newStatus));
    }

    @PatchMapping(value = "/text/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateTaskText(@PathVariable Long id,
                                         @RequestBody @Valid ChangeTextTodoDto newText)
                                        throws CustomException {
            return ResponseEntity.ok(taskService.updateTaskText(id, newText));
    }

    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateStatusAllTasks(@RequestBody @Valid ChangeStatusTodoDto newStatus) {
            return ResponseEntity.ok(taskService.updateStatusAllTasks(newStatus));
    }
}
