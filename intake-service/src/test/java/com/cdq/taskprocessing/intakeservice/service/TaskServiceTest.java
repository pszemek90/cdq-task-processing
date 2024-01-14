package com.cdq.taskprocessing.intakeservice.service;

import com.cdq.taskprocessing.database.dao.TaskDao;
import com.cdq.taskprocessing.database.entity.Task;
import com.cdq.taskprocessing.intakeservice.model.Tasks;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TaskService.class)
class TaskServiceTest {

    @MockBean
    private TaskDao taskDao;
    @MockBean
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private TaskService taskService;

    @Test
    void shouldReturnMappedTasks_WhenNonEmptyListReturnedFromDb() {
        //given
        Task task = new Task();
        task.setId(UUID.fromString("d7577be5-dc15-4ab1-b075-7d3d5b3177ba"));
        Task task1 = new Task();
        task1.setId(UUID.fromString("03657c80-a2b6-4d0f-9390-2983f6a56562"));
        when(taskDao.getAllTasks()).thenReturn(List.of(task, task1));
        //when
        Tasks tasks = taskService.getAllTasks();
        //then
        assertEquals(2, tasks.tasks().size());
    }
}