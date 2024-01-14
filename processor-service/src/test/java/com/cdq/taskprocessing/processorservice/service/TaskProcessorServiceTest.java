package com.cdq.taskprocessing.processorservice.service;

import com.cdq.taskprocessing.database.dao.TaskDao;
import com.cdq.taskprocessing.processorservice.model.ProcessingResult;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@EmbeddedKafka
class TaskProcessorServiceTest {

    @MockBean
    private TaskDao taskDao;

    @InjectMocks
    @Autowired
    private TaskProcessorService taskProcessorService;


    @ParameterizedTest
    @CsvSource(
            {
            "203a0bdf-f8c1-4da6-9545-c823dfde241c,ABCD,BCD,1,0",
            "2c958fde-2cc1-4753-b608-d5edd8c04e36,ABCD,BWD,1,1",
            "288b2b4a-5173-4ab2-a83c-e2ca038b7e8d,ABCDEFG,CFG,4,1",
            "03657c80-a2b6-4d0f-9390-2983f6a56562,ABCABC,ABC,0,0",
            "d7577be5-dc15-4ab1-b075-7d3d5b3177ba,ABCDEFG,TDD,1,2"
            }
    )
    void processTask(UUID uuid, String input, String pattern, int bestPosition, int typos) {
        //when
        ProcessingResult processingResult = taskProcessorService.processTask(uuid, input, pattern);
        //then
        assertEquals(bestPosition, processingResult.bestPosition());
        assertEquals(typos, processingResult.typos());
    }
}