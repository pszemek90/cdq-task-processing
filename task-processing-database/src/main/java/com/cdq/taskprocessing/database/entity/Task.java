package com.cdq.taskprocessing.database.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "tasks")
@Entity
public class Task {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;
    private String input;
    private String pattern;
    @Column(name = "best_position")
    private Integer bestPosition;
    private Integer typos;
    private int progress;
    @Column(name = "created_date")
    private String createdDate;
    @Column(name = "modified_date")
    private String modifiedDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
