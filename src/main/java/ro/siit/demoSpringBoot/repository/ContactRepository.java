package ro.siit.demoSpringBoot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ro.siit.demoSpringBoot.entity.Contact;

import java.util.Collection;
import java.util.UUID;

public interface ContactRepository extends CrudRepository<Contact, UUID> {

    @Query("SELECT c FROM Contact c WHERE c.name LIKE '%F%'")
    Collection<Contact> findAllActiveUsers();
}
