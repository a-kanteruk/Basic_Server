package net.dunice.Basic_server.controller;

import jakarta.validation.Valid;
import net.dunice.Basic_server.dto.ChangeStatusTodoDto;
import net.dunice.Basic_server.dto.ChangeTextTodoDto;
import net.dunice.Basic_server.dto.CreateTodoDto;
import net.dunice.Basic_server.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/todo")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity addTask(@RequestBody @Valid CreateTodoDto task){
        try{
            taskService.addTask(task);
            return ResponseEntity.ok("Your task added.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Something wrong.");
        }
    }

    @GetMapping
    public ResponseEntity getTask(@RequestParam Long id){
        try{
            return ResponseEntity.ok(taskService.getTask(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Something wrong.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id){
        try {
            return ResponseEntity.ok(taskService.deleteTask(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Something wrong.");
        }
    }

    @DeleteMapping
    public ResponseEntity deleteAllTasks(){
        try {
            return ResponseEntity.ok(taskService.deleteAllTasks());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Something wrong.");
        }
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity updateTaskStatus(@PathVariable Long id, @RequestBody @Valid ChangeStatusTodoDto newStatus){
        try{
            return ResponseEntity.ok(taskService.updateTaskStatus(id, newStatus));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Something wrong.");
        }
    }

    @PatchMapping("/text/{id}")
    public ResponseEntity updateTaskText(@PathVariable Long id, @RequestBody @Valid ChangeTextTodoDto newText){
        try{
            return ResponseEntity.ok(taskService.updateTaskText(id, newText));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Something wrong.");
        }
    }

    @PatchMapping
    public ResponseEntity updateStatusAllTasks(@RequestBody @Valid ChangeStatusTodoDto newStatus){
        try{
            return ResponseEntity.ok(taskService.updateStatusAllTasks(newStatus));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Something wrong.");
        }
    }
}
