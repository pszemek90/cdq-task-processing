package com.cdq.taskprocessing.intakeservice.service;

import com.cdq.taskprocessing.model.TaskMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);

    @Value("${kafka.task.topic.name}")
    private String taskTopicName;

    private final KafkaTemplate<String, TaskMessage> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, TaskMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(TaskMessage message) {
        log.info("Sending message: {}", message);
        kafkaTemplate.send(taskTopicName, message);
    }
}
