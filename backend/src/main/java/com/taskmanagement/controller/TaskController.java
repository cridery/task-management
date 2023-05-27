package com.taskmanagement.controller;

import com.taskmanagement.model.Task;
import com.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Optional<Task> updatedTask = taskService.updateTask(id, task);
        if(updatedTask.isPresent()){
            return ResponseEntity.ok(updatedTask.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteTask(@PathVariable Long id) {
        boolean isDeleted = taskService.deleteTask(id);

        Map<String, Object> response = new HashMap<>();
        response.put("success", isDeleted);
        return isDeleted ?
                new ResponseEntity<>(response, HttpStatus.OK) :
                new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
