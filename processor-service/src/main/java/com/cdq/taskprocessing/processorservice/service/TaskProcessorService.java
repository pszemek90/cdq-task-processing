package com.cdq.taskprocessing.processorservice.service;

import com.cdq.taskprocessing.processorservice.model.ProcessingResult;
import org.springframework.stereotype.Service;

@Service
public class TaskProcessorService {

    public ProcessingResult processTask(String input, String pattern) {
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
        }
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
