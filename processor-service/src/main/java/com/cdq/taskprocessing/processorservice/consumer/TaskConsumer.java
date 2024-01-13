package com.cdq.taskprocessing.processorservice.consumer;

import com.cdq.taskprocessing.model.TaskMessage;
import com.cdq.taskprocessing.processorservice.model.ProcessingResult;
import com.cdq.taskprocessing.processorservice.service.TaskProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TaskConsumer {

    private static final Logger log = LoggerFactory.getLogger(TaskConsumer.class);

    private final TaskProcessorService taskProcessorService;
    private TaskMessage payload;
    private boolean isMessageConsumed;

    public TaskConsumer(TaskProcessorService taskProcessorService) {
        this.taskProcessorService = taskProcessorService;
    }

    @KafkaListener(topics = "${kafka.task.topic.name}", groupId = "${kafka.task.consumer.group-id}")
    public void consumeMessage(TaskMessage message) {
        log.info("Consuming message: {}", message);
        isMessageConsumed = true;
        payload = message;
        ProcessingResult processingResult = taskProcessorService.processTask(message.input(), message.pattern());
    }

    TaskMessage getPayload() {
        return payload;
    }

    boolean isMessageConsumed() {
        return isMessageConsumed;
    }
}
