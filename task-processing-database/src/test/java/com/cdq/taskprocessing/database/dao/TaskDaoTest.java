package com.cdq.taskprocessing.database.dao;

import com.cdq.taskprocessing.database.entity.Task;
import com.cdq.taskprocessing.database.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase
class TaskDaoTest {

    @SpyBean
    private TaskRepository taskRepository;

    @InjectMocks
    @Autowired
    private TaskDao taskDao;

    @Test
    void shouldUpdateTaskProgress_WhenTaskFoundInDb() {
        //given
        UUID uuid = UUID.fromString("94158c76-7614-4f94-aad4-5f0918afb5f6");
        Task task = new Task();
        task.setId(uuid);
        when(taskRepository.findById(uuid)).thenReturn(Optional.of(task));
        //when
        taskDao.updateProgress(uuid, 100);
        //then
        verify(taskRepository).save(task);
    }

    @Test
    void shouldThrowException_WhenTaskNotFoundInDb() {
        //given
        UUID uuid = UUID.fromString("94158c76-7614-4f94-aad4-5f0918afb5f6");
        when(taskRepository.findById(uuid)).thenReturn(Optional.empty());
        //when, then
        assertThrows(NoSuchElementException.class, () -> taskDao.updateProgress(uuid, 100));
    }
}