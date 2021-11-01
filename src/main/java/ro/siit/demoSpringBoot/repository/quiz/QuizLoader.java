package ro.siit.demoSpringBoot.repository.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ro.siit.demoSpringBoot.entity.quiz.Answer;
import ro.siit.demoSpringBoot.entity.quiz.Question;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class QuizLoader {

    @Autowired
    QuestionRepository questionRepository;

    @EventListener
    public void loadInitialData(ApplicationReadyEvent event){
        Question q1 = new Question();
        q1.setId(UUID.randomUUID());
        q1.setText("Wie geht es ... ?");

        Set<Answer> answerSet1 = new HashSet<>();

        Answer a11 = new Answer();
        a11.setId(UUID.randomUUID());
        a11.setCorrect(true);
        a11.setText("dir");
        a11.setQuestion(q1);
        answerSet1.add(a11);

        Answer a12 = new Answer();
        a12.setId(UUID.randomUUID());
        a12.setCorrect(false);
        a12.setText("mir");
        a12.setQuestion(q1);
        answerSet1.add(a12);

        q1.setAnswers(answerSet1);
        questionRepository.save(q1);


        Question q2 = new Question();
        q2.setId(UUID.randomUUID());
        q2.setText("Wie heissen ...?");

        Set<Answer> answerSet2 = new HashSet<>();

        Answer a21 = new Answer();
        a21.setId(UUID.randomUUID());
        a21.setCorrect(true);
        a21.setText("Sie");
        a21.setQuestion(q2);
        answerSet2.add(a21);

        Answer a22 = new Answer();
        a22.setId(UUID.randomUUID());
        a22.setCorrect(false);
        a22.setText("Ihnen");
        a22.setQuestion(q2);
        answerSet2.add(a22);

        q2.setAnswers(answerSet2);
        questionRepository.save(q2);

    }
}
