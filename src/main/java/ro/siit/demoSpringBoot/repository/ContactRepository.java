package ro.siit.demoSpringBoot.repository;

import org.springframework.data.repository.CrudRepository;
import ro.siit.demoSpringBoot.entity.Contact;

import java.util.UUID;

public interface ContactRepository extends CrudRepository<Contact, UUID> {

}
