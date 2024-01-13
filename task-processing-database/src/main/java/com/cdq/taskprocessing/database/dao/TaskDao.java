package com.cdq.taskprocessing.database.dao;

import com.cdq.taskprocessing.database.entity.Task;
import com.cdq.taskprocessing.database.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class TaskDao {
    
    private final TaskRepository taskRepository;

    public TaskDao(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public UUID createTask(String input, String pattern) {
        Task task = new Task();
        task.setInput(input);
        task.setPattern(pattern);
        task.setCreatedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        Task savedTask = taskRepository.save(task);
        return savedTask.getId();
    }
}
