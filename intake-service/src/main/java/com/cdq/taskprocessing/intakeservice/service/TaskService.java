package com.cdq.taskprocessing.intakeservice.service;

import com.cdq.taskprocessing.database.dao.TaskDao;
import com.cdq.taskprocessing.intakeservice.mapper.TaskMapper;
import com.cdq.taskprocessing.intakeservice.model.Tasks;
import com.cdq.taskprocessing.model.TaskMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    private final TaskDao taskDao;
    private final KafkaProducerService kafkaProducerService;

    public TaskService(TaskDao taskDao, KafkaProducerService kafkaProducerService) {
        this.taskDao = taskDao;
        this.kafkaProducerService = kafkaProducerService;
    }

    public UUID createTask(String input, String pattern) {
        UUID taskId = taskDao.createTask(input, pattern);
        log.info("Created task with id: {}, for input: {} and pattern: {}", taskId, input, pattern);
        kafkaProducerService.sendMessage(new TaskMessage(taskId, input, pattern));
        log.debug("Message sent to Kafka topic.");
        return taskId;
    }

    public Tasks getAllTasks() {
        return new Tasks(TaskMapper.mapToDto(taskDao.getAllTasks()));
    }

    public Tasks getTask(UUID id) {
        return new Tasks(List.of(TaskMapper.mapToDto(taskDao.getTaskById(id))));
    }
}
