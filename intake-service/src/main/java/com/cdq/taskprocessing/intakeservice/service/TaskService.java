package com.cdq.taskprocessing.intakeservice.service;

import com.cdq.taskprocessing.intakeservice.database.dao.TaskDao;
import com.cdq.taskprocessing.model.TaskMessage;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskService {

    private final TaskDao taskDao;
    private final KafkaProducerService kafkaProducerService;

    public TaskService(TaskDao taskDao, KafkaProducerService kafkaProducerService) {
        this.taskDao = taskDao;
        this.kafkaProducerService = kafkaProducerService;
    }

    public UUID createTask(String input, String pattern) {
        UUID taskId = taskDao.createTask(input, pattern);
        kafkaProducerService.sendMessage(new TaskMessage(taskId, input, pattern));
        return taskId;
    }
}
