package com.cdq.taskprocessing.processorservice.service;

import com.cdq.taskprocessing.database.dao.TaskDao;
import com.cdq.taskprocessing.processorservice.model.ProcessingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class TaskProcessorService {

    private static final Logger log = LoggerFactory.getLogger(TaskProcessorService.class);
    @Value("${app.task.delay}")
    private int taskDelay;

    private final TaskDao taskDao;
    private static final Random random = new Random();
    public TaskProcessorService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public ProcessingResult processTask(UUID uuid, String input, String pattern) {
        log.info("Processing task: {}", uuid);
        int progress = 0;
        int inputLength = input.length();
        int patternLength = pattern.length();
        int bestPosition = -1;
        int minTypos = Integer.MAX_VALUE;

        for (int i = 0; i <= inputLength - patternLength; i++) {
            int typos = calculateTypos(input.substring(i, i + patternLength), pattern);
            if (typos < minTypos) {
                minTypos = typos;
                bestPosition = i;
            }
            if(random.nextBoolean()) {
                try {
                    Thread.sleep(taskDelay);
                } catch (InterruptedException e) {
                    log.warn("Exception occurred during sleep: {}", e.getMessage());
                }
                progress += 10;
                if(progress < 100) {
                    log.info("Updating progress of task: {}. Progress: {}", uuid, progress);
                    taskDao.updateProgress(uuid, progress);
                }
            }
            progress += 10;
        }
        log.info("Task {} finished processing", uuid);
        taskDao.saveResults(uuid, bestPosition, minTypos);
        return new ProcessingResult(bestPosition, minTypos);
    }

    private int calculateTypos(String substring, String pattern) {
        int typos = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (substring.charAt(i) != pattern.charAt(i)) {
                typos++;
            }
        }
        return typos;
    }
}
