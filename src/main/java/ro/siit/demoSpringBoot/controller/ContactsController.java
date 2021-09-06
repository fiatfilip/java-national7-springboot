package ro.siit.demoSpringBoot.controller;

import jdk.jfr.Percentage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.siit.demoSpringBoot.entity.Contact;
import ro.siit.demoSpringBoot.repository.ContactRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("contacts")
public class ContactsController {

    @Autowired
    private ContactRepository repository;

    @GetMapping({"/", ""})
    public String list(Model model){
        //  repository.save(new Contact(UUID.randomUUID(), "nameX", "surnameX", "emailX", "phoneX", "profileX"));
        Iterable<Contact> contactsList = repository.findAll();
        //
        model.addAttribute("contactsList", contactsList /*new  ArrayList<Contact>()*/);
        return "contacts/list";
    }

    @GetMapping({"/add"})
    public String displayAddForm() {
        return "contacts/form";
    }

    @PostMapping({"/add"})
    public String addContact(@Param("name") String name,
                             @Param("surname") String surname,
                             @Param("email") String email,
                             @Param("phone") String phone,
                             Model model) {
        repository.save(new Contact(UUID.randomUUID(), name, surname, email, phone, "profileX"));
        Iterable<Contact> contactsList = repository.findAll();
        model.addAttribute("contactsList", contactsList /*new  ArrayList<Contact>()*/);
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
        model.addAttribute("contact", contact);
        return "contacts/form";
    }

    @PostMapping({"/edit"})
    public String editContact(@Param("id") String id,
                              @Param("name") String name,
                              @Param("surname") String surname,
                              @Param("email") String email,
                              @Param("phone") String phone, Model model) {
        repository.save(new Contact(UUID.fromString(id), name, surname, email, phone, "profileX"));
        Iterable<Contact> contactsList = repository.findAll();
        model.addAttribute("contactsList", contactsList );
        return "contacts/list";
    }
}
