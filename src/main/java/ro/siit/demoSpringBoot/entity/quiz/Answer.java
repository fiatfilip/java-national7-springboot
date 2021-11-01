package ro.siit.demoSpringBoot.entity.quiz;

import ro.siit.demoSpringBoot.entity.Contact;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "answers")
public class Answer {
    @Id
    private UUID id;
    private String text;
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private Question question;

    public Answer() {}

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

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }
}
