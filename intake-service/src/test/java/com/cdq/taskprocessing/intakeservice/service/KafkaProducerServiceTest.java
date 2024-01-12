package com.cdq.taskprocessing.intakeservice.service;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:0", "port=0"})
class KafkaProducerServiceTest {

    @Value("${kafka.task.topic.name}")
    private String taskTopicName;

    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;
    private Consumer<String, String> consumer;
    @Autowired
    private ConsumerFactory<String, String> consumerFactory;

    @BeforeEach
    void setUp() {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("test-group-id", "true", embeddedKafkaBroker);
        consumer = new KafkaConsumer<>(consumerProps);
        consumer.subscribe(Collections.singletonList(taskTopicName));
    }

    @AfterEach
    void tearDown() {
        consumer.close();
    }

    @Test
    void sendTestMessage() {
        kafkaProducerService.sendMessage("testMessage");
        ConsumerRecords<String, String> records = KafkaTestUtils.getRecords(consumer, Duration.ofSeconds(5));
        assertEquals(1, records.count());
        assertEquals("testMessage", records.iterator().next().value());
    }

}