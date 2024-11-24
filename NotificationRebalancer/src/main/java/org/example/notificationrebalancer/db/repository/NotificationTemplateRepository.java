package org.example.notificationrebalancer.db.repository;

import org.example.notificationrebalancer.db.entity.NotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate,Long> {
}
