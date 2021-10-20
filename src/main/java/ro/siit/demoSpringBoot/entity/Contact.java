package ro.siit.demoSpringBoot.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    private UUID id;

    @Column(name="custom_name")
    private String name;

    private String surname;

    @Column(length = 64)
    private String emailAddress;

    // @Column(length = 32)
    @OneToMany(cascade = {CascadeType.ALL})
    private Set<PhoneNumber> phoneNumbers;
    private String profilePhoto; // "Gica.jpg" <img src="${profilePhoto}>

    public UUID getId() {
        return id;
    }

    public Contact() {}

    public Contact(UUID id, String name, String surname, String emailAddress, String profilePhoto) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.profilePhoto = profilePhoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Set<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
