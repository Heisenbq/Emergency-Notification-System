package org.coursework.notificationservice.db.repository;

import org.coursework.notificationservice.db.entity.NotificationSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationSessionRepository extends JpaRepository<NotificationSession,Long> {
}
