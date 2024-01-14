package com.cdq.taskprocessing.database.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
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
    private LocalDateTime createdDate;
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
