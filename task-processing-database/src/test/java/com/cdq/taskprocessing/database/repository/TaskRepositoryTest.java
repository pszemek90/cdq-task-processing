package com.cdq.taskprocessing.database.repository;

import com.cdq.taskprocessing.database.entity.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @AfterEach
    void tearDown() {
        taskRepository.deleteAll();
    }

    @Test
    void shouldReturnTaskWithNonNullId_WhenSaveRequestSuccessful() {
        //given
        Task task = new Task();
        task.setInput("testInput");
        task.setPattern("testPattern");
        task.setCreatedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        //when
        Task savedTask = taskRepository.save(task);
        //then
        assertNotNull(savedTask.getId());
        assertEquals(task.getInput(), savedTask.getInput());
        assertEquals(task.getPattern(), savedTask.getPattern());
        assertNotNull(savedTask.getCreatedDate());
        assertNull(savedTask.getBestPosition());
        assertNull(savedTask.getTypos());
        assertNull(savedTask.getModifiedDate());
        assertEquals(0, savedTask.getProgress());
    }

    @Test
    void shouldUpdateTaskProgress_WhenTaskFoundInDb() {
        Task task = new Task();
        Task savedTask = taskRepository.saveAndFlush(task);
        assertEquals(0, savedTask.getProgress());
        Optional<Task> taskToUpdate = taskRepository.findById(savedTask.getId());
        assertTrue(taskToUpdate.isPresent());
        Task taskFound = taskToUpdate.get();
        taskFound.setProgress(10);
        Task updatedTask = taskRepository.saveAndFlush(taskFound);
        assertEquals(10, updatedTask.getProgress());
    }

    @Test
    void shouldReturnTwoTasks_WhenTwoTasksSaved() {
        Task task = new Task();
        taskRepository.saveAndFlush(task);
        Task task1 = new Task();
        taskRepository.saveAndFlush(task1);
        List<Task> persistedTasks = taskRepository.findAll();
        assertEquals(2, persistedTasks.size());
    }

}