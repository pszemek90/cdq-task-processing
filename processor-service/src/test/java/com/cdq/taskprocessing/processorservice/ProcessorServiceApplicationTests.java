package com.cdq.taskprocessing.processorservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@AutoConfigureTestDatabase
@EmbeddedKafka
class ProcessorServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
