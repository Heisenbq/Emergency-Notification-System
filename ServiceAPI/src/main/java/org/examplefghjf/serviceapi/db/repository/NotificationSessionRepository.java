package org.examplefghjf.serviceapi.db.repository;

import org.examplefghjf.serviceapi.db.entity.NotificationSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationSessionRepository extends JpaRepository<NotificationSession,Long> {
}
