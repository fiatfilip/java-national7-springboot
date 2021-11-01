package ro.siit.demoSpringBoot.entity.quiz;

import ro.siit.demoSpringBoot.entity.PhoneNumber;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="questions")
public class Question {

    @Id
    private UUID id;
    private String text;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Answer> answers;

    public Question(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }
}
