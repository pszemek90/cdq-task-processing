package com.cdq.taskprocessing.database.dao;

import com.cdq.taskprocessing.database.entity.Task;
import com.cdq.taskprocessing.database.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase
class TaskDaoTest {

    @Autowired
    private TaskRepository taskRepository;

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
        assertNull(savedTask.getBestMatch());
        assertNull(savedTask.getTypos());
        assertNull(savedTask.getModifiedDate());
        assertEquals(0, savedTask.getProgress());
    }
}