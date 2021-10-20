package ro.siit.demoSpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.siit.demoSpringBoot.entity.Contact;
import ro.siit.demoSpringBoot.entity.PhoneNumber;
import ro.siit.demoSpringBoot.repository.ContactRepository;

import java.util.*;

@Controller
@RequestMapping("contacts")
public class ContactsController {

    @Autowired
    private ContactRepository repository;

    @GetMapping({"/", ""})
    public String list(Model model){
        Iterable<Contact> contactsList = repository.findAll();
        model.addAttribute("contactsList", contactsList);
        return "contacts/list";
    }

    @GetMapping({"/add"})
    public String displayAddForm() {
        return "contacts/add_form";
    }

    @PostMapping({"/add"})
    public String addContact(@Param("name") String name,
                             @Param("surname") String surname,
                             @Param("email") String email,
                             @Param("phone") String phone,
                             Model model) {
        Contact contact = new Contact(UUID.randomUUID(), name, surname, email, "profileX");

        Set<PhoneNumber> phones = new HashSet<>();
        PhoneNumber phoneNumber = new PhoneNumber(UUID.randomUUID(), phone);
        phoneNumber.setContact(contact);
        phones.add(phoneNumber);

        contact.setPhoneNumbers(phones);

        repository.save(contact);

        Iterable<Contact> contactsList = repository.findAll();
        model.addAttribute("contactsList", contactsList);
        return "contacts/list";
    }

    @GetMapping({"/delete"})
    public String deleteContact(@RequestParam("contact_id") String id, Model model) {
        repository.deleteById(UUID.fromString(id));
        Iterable<Contact> contactsList = repository.findAll();
        model.addAttribute("contactsList", contactsList );
        return "contacts/list";
    }

    @GetMapping({"/edit"})
    public String showEditForm(@RequestParam("contact_id") String id, Model model) {
        Optional<Contact> contact = repository.findById(UUID.fromString(id));
        model.addAttribute("contact", contact.isPresent() ? contact.get(): null);
        return "contacts/edit_form";
    }

    @PostMapping({"/edit"})
    public String editContact(@Param("id") String id,
                              @Param("name") String name,
                              @Param("surname") String surname,
                              @Param("email") String email,
                              @Param("phone") String phone, Model model) {
        Optional<Contact> contactToBeUpdated = repository.findById(UUID.fromString(id));
        if(contactToBeUpdated.isPresent()) {
            repository.save(new Contact(UUID.fromString(id), name, surname, email, "profileX"));
            //List<PhoneNumber> phones = new ArrayList<>();
            // phones.add(new PhoneNumber(UUID))
        }
        Iterable<Contact> contactsList = repository.findAll();
        model.addAttribute("contactsList", contactsList );
        return "contacts/list";
    }
}
