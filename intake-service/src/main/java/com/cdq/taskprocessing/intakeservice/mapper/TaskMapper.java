package com.cdq.taskprocessing.intakeservice.mapper;

import com.cdq.taskprocessing.database.entity.Task;
import com.cdq.taskprocessing.intakeservice.model.TaskDto;

import java.util.List;

public class TaskMapper {

    private TaskMapper() {}

    public static TaskDto mapToDto(Task entity){
        TaskDto dto = new TaskDto();
        dto.setId(entity.getId().toString());
        dto.setInput(entity.getInput());
        dto.setPattern(entity.getPattern());
        dto.setBestPosition(entity.getBestPosition());
        dto.setTypos(entity.getTypos());
        dto.setProgress(entity.getProgress());
        return dto;
    }

    public static List<TaskDto> mapToDto(List<Task> entities) {
        return entities.stream().map(TaskMapper::mapToDto).toList();
    }
}
