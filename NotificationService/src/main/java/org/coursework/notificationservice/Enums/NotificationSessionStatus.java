package org.coursework.notificationservice.Enums;

public enum NotificationSessionStatus {
    PENDING("pending"),
    IN_PROGRESS("in_progress"),
    FAILED("failed"),
    COMPLETED("completed");
    private final String status;
    NotificationSessionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
