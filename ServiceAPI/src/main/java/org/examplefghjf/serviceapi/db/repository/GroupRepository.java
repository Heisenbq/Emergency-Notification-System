package org.examplefghjf.serviceapi.db.repository;

import org.examplefghjf.serviceapi.db.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
}
