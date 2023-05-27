package com.taskmanagement.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, updatable = false)
    protected LocalDateTime creationDate;

    @Column(nullable = false)
    protected LocalDateTime lastModifiedDate;

    @PrePersist
    protected void onCreation() {
        LocalDateTime now = LocalDateTime.now();
        creationDate = now;
        lastModifiedDate = now;
    }

    @PreUpdate
    protected void onUpdate() {
        lastModifiedDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}