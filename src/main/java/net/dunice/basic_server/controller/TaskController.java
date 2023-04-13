package net.dunice.basic_server.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import net.dunice.basic_server.constants.ValidationConstants;
import net.dunice.basic_server.dto.ChangeStatusTodoDto;
import net.dunice.basic_server.dto.ChangeTextTodoDto;
import net.dunice.basic_server.dto.CreateTodoDto;
import net.dunice.basic_server.exception.CreatePostException;
import net.dunice.basic_server.exception.CustomException;
import net.dunice.basic_server.exception.CustomExceptionBoolean;
import net.dunice.basic_server.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/todo")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addTask(@RequestBody @Valid CreateTodoDto task) throws CreatePostException {
            return ResponseEntity.ok(taskService.addTask(task));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTask(@RequestParam Long id){
            return ResponseEntity.ok(taskService.getTask(id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteTask(@Valid @PathVariable @Positive(message = ValidationConstants.ID_MUST_BE_POSITIVE) Long id) throws CustomException {
            return ResponseEntity.ok(taskService.deleteTask(id));
    }

    @DeleteMapping
    public ResponseEntity deleteAllTasks(){
            return ResponseEntity.ok(taskService.deleteAllTasks());
    }

    @PatchMapping(value = "/status/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateTaskStatus(@PathVariable Long id,
                                           @RequestBody @Valid ChangeStatusTodoDto newStatus)
                                            throws CustomException, CustomExceptionBoolean {

            return ResponseEntity.ok(taskService.updateTaskStatus(id, newStatus));
    }

    @PatchMapping(value = "/text/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateTaskText(@PathVariable Long id,
                                         @RequestBody @Valid ChangeTextTodoDto newText)
                                        throws CustomException {
            return ResponseEntity.ok(taskService.updateTaskText(id, newText));
    }

    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateStatusAllTasks(@RequestBody @Valid ChangeStatusTodoDto newStatus){
            return ResponseEntity.ok(taskService.updateStatusAllTasks(newStatus));
    }
}
