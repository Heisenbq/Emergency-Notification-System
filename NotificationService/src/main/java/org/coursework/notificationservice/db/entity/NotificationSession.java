package org.coursework.notificationservice.db.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Table(name = "notificationsessions")
public class NotificationSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Long id;

    private Long templateId;
    private Long groupId;
    private String status;
    private Timestamp createdAt;
    private Timestamp startedAt;
    private Timestamp completedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getStartedAt() {
        return startedAt;
    }

    @PrePersist
    public void onCreatedAt() {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public Timestamp getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Timestamp completedAt) {
        this.completedAt = completedAt;
    }
}