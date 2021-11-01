package ro.siit.demoSpringBoot.repository.quiz;


import org.springframework.data.repository.CrudRepository;
import ro.siit.demoSpringBoot.entity.quiz.Answer;
import ro.siit.demoSpringBoot.entity.quiz.Question;

import java.util.UUID;

public interface AnswerRepository extends CrudRepository<Answer, UUID> {

}
