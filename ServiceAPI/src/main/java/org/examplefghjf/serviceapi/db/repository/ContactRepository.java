package org.examplefghjf.serviceapi.db.repository;

import org.examplefghjf.serviceapi.db.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {

}
