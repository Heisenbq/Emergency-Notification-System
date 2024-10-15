package org.coursework.notificationservice.db.repository;

import org.coursework.notificationservice.db.entity.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<Group,Long> {
}
