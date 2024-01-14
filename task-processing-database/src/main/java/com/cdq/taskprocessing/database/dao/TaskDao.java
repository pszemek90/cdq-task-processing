package com.cdq.taskprocessing.database.dao;

import com.cdq.taskprocessing.database.entity.Task;
import com.cdq.taskprocessing.database.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
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

    public void updateProgress(UUID uuid, int progress) {
        Task taskToUpdate = getTaskById(uuid);
        taskToUpdate.setProgress(progress);
        taskRepository.save(taskToUpdate);
    }

    public void saveResults(UUID uuid, int bestPosition, int typos) {
        Task taskToUpdate = getTaskById(uuid);
        taskToUpdate.setBestPosition(bestPosition);
        taskToUpdate.setTypos(typos);
        taskToUpdate.setProgress(100);
        taskRepository.save(taskToUpdate);
    }

    private Task getTaskById(UUID uuid) {
        return taskRepository.findById(uuid).orElseThrow(() -> new NoSuchElementException("No task found with id: " + uuid));
    }
}
