package org.example.notificationrebalancer.db.repository;

import org.example.notificationrebalancer.db.entity.NotificationSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationSessionRepository extends JpaRepository<NotificationSession,Long> {
}
