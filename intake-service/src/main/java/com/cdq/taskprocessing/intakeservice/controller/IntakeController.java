package com.cdq.taskprocessing.intakeservice.controller;

import com.cdq.taskprocessing.intakeservice.model.CreateTaskRequest;
import com.cdq.taskprocessing.intakeservice.model.Tasks;
import com.cdq.taskprocessing.intakeservice.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class IntakeController {

    private final TaskService taskService;

    public IntakeController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTask(@RequestBody CreateTaskRequest request) {
        UUID uuid = taskService.createTask(request.input(), request.pattern());
        return ResponseEntity.created(URI.create("/tasks/" + uuid)).build();
    }

    @GetMapping
    public ResponseEntity<Tasks> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
}
