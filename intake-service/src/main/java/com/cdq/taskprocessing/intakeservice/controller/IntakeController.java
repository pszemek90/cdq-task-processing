package com.cdq.taskprocessing.intakeservice.controller;

import com.cdq.taskprocessing.intakeservice.model.CreateTaskRequest;
import com.cdq.taskprocessing.intakeservice.model.Tasks;
import com.cdq.taskprocessing.intakeservice.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@Validated
public class IntakeController {

    private final TaskService taskService;

    public IntakeController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTask(@Valid @RequestBody CreateTaskRequest request) {
        validateInput(request);
        UUID uuid = taskService.createTask(request.input(), request.pattern());
        return ResponseEntity.created(URI.create("/tasks/" + uuid)).build();
    }

    private void validateInput(CreateTaskRequest request) {
        String input = request.input();
        String pattern = request.pattern();
        if(pattern.length() > input.length()) {
            throw new IllegalStateException("Pattern cannot be longer than input!");
        }
    }

    @GetMapping
    public ResponseEntity<Tasks> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tasks> getTask(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }
}
