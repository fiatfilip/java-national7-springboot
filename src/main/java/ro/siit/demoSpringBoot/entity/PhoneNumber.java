package ro.siit.demoSpringBoot.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="phone_numbers")
public class PhoneNumber {
    @Id
    private UUID id;

    @Column(name="number")
    private String number;

    @ManyToOne
    @JoinColumn(name="contact_id", nullable=false)
    private Contact contact;

    public PhoneNumber(){

    }

    public PhoneNumber(UUID id, String number){
        this.id = id;
        this.number = number;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return  number ;
    }
}
