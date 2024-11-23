package org.example.notificationrebalancer.db.repository;

import org.example.notificationrebalancer.db.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
}
