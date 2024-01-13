package com.cdq.taskprocessing.processorservice.service;

import com.cdq.taskprocessing.processorservice.model.ProcessingResult;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class TaskProcessorServiceTest {

    private static TaskProcessorService taskProcessorService;

    @BeforeAll
    static void setUp() {
        taskProcessorService = new TaskProcessorService();
    }

    @ParameterizedTest
    @CsvSource(
            {
            "ABCD,BCD,1,0",
            "ABCD,BWD,1,1",
            "ABCDEFG,CFG,4,1",
            "ABCABC,ABC,0,0",
            "ABCDEFG,TDD,1,2"
            }
    )
    void processTask(String input, String pattern, int bestPosition, int typos) {
        //when
        ProcessingResult processingResult = taskProcessorService.processTask(input, pattern);
        //then
        assertEquals(bestPosition, processingResult.bestPosition());
        assertEquals(typos, processingResult.typos());
    }
}