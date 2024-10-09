package org.examplefghjf.serviceapi.db.repository;

import org.examplefghjf.serviceapi.db.entity.NotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate,Long> {
}
