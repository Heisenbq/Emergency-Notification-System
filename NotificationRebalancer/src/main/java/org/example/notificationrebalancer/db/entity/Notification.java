package org.example.notificationrebalancer.db.entity;

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
    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

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

    public Contact getContact() {
        return contact;
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

}
