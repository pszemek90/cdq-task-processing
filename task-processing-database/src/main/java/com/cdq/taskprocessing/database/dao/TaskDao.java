package com.cdq.taskprocessing.database.dao;

import com.cdq.taskprocessing.database.entity.Task;
import com.cdq.taskprocessing.database.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
        task.setCreatedDate(LocalDateTime.now());
        Task savedTask = taskRepository.save(task);
        return savedTask.getId();
    }

    public void updateProgress(UUID uuid, int progress) {
        Task taskToUpdate = getTaskById(uuid);
        taskToUpdate.setProgress(progress);
        taskToUpdate.setModifiedDate(LocalDateTime.now());
        taskRepository.save(taskToUpdate);
    }

    public void saveResults(UUID uuid, int bestPosition, int typos) {
        Task taskToUpdate = getTaskById(uuid);
        taskToUpdate.setBestPosition(bestPosition);
        taskToUpdate.setTypos(typos);
        taskToUpdate.setProgress(100);
        taskToUpdate.setModifiedDate(LocalDateTime.now());
        taskRepository.save(taskToUpdate);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(UUID uuid) {
        return taskRepository.findById(uuid).orElseThrow(() -> new NoSuchElementException("No task found with id: " + uuid));
    }
}
