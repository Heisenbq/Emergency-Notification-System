package org.coursework.notificationservice.db.repository;

import org.coursework.notificationservice.db.entity.NotificationTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTemplateRepository extends CrudRepository<NotificationTemplate,Long> {
}
