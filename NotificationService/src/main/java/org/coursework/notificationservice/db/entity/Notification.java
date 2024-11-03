package org.coursework.notificationservice.db.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    private Long sessionId;
    private Long contactId;

    private String status;
    private String errorMessage;
    private Integer sendAttempts;
    private Timestamp lastAttemptAt;
    private Timestamp createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getSendAttempts() {
        return sendAttempts;
    }

    public void setSendAttempts(Integer sendAttempts) {
        this.sendAttempts = sendAttempts;
    }

    public Timestamp getLastAttemptAt() {
        return lastAttemptAt;
    }

    public void setLastAttemptAt(Timestamp lastAttemptAt) {
        this.lastAttemptAt = lastAttemptAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    public void onCreatedAt(){
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Notifications{" +
                "id=" + id +
                ", sessionId=" + sessionId +
                ", contactId=" + contactId +
                ", status='" + status + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", sendAttempts=" + sendAttempts +
                ", lastAttemptAt=" + lastAttemptAt +
                ", createdAt=" + createdAt +
                '}';
    }
}
