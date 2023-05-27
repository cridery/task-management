package com.taskmanagement.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Task extends BaseEntity{

    private String title;
    private String description;
    @Column(nullable = false)
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public enum TaskStatus {
        TODO,
        IN_PROGRESS,
        DONE
    }

    public Task() {
     super();
    }

    public Task(String title, String description, LocalDateTime dueDate, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }


    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
