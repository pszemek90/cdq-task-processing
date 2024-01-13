package com.cdq.taskprocessing.processorservice.consumer;

import com.cdq.taskprocessing.model.TaskMessage;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:0", "port=0"})
class TaskConsumerTest {

    @Value("${kafka.task.topic.name}")
    private String taskTopicName;
    @Autowired
    private TaskConsumer taskConsumer;
    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;
    private Producer<String, TaskMessage> producer;

    @BeforeEach
    void setUp() {
        Map<String, Object> producerProps = KafkaTestUtils.producerProps(embeddedKafkaBroker);
        producer = new KafkaProducer<>(producerProps, new StringSerializer(), new JsonSerializer<>());
    }

    @Test
    void consumeTestMessage() throws Exception{
        TaskMessage testMessage = new TaskMessage(UUID.randomUUID(), "testInput", "testPattern");
        producer.send(new ProducerRecord<>(taskTopicName, testMessage));
        Thread.sleep(5000);
        assertTrue(taskConsumer.isMessageConsumed());
        assertEquals("testInput", taskConsumer.getPayload().input());
        assertEquals("testPattern", taskConsumer.getPayload().pattern());
    }
}