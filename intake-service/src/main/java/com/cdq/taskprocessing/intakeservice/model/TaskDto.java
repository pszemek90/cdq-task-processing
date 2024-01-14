package com.cdq.taskprocessing.intakeservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDto {
    private String id;
    private String input;
    private String pattern;
    @JsonProperty("best_position")
    private Integer bestPosition;
    private Integer typos;
    private int progress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Integer getBestPosition() {
        return bestPosition;
    }

    public void setBestPosition(Integer bestPosition) {
        this.bestPosition = bestPosition;
    }

    public Integer getTypos() {
        return typos;
    }

    public void setTypos(Integer typos) {
        this.typos = typos;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "id='" + id + '\'' +
                ", input='" + input + '\'' +
                ", pattern='" + pattern + '\'' +
                ", bestPosition=" + bestPosition +
                ", typos=" + typos +
                ", progress=" + progress +
                '}';
    }
}
