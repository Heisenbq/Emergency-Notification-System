package org.example.notificationrebalancer.db.repository;

import org.example.notificationrebalancer.db.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findByStatus(String status);
    @Query("SELECT u FROM Notification u WHERE u.status = :status AND u.sendAttempts < 5")
    List<Notification> findByStatusWithAttemptsLessThan5(@Param("status") String status);
}
