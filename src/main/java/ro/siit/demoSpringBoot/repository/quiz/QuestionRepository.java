package ro.siit.demoSpringBoot.repository.quiz;


import org.springframework.data.repository.CrudRepository;

import ro.siit.demoSpringBoot.entity.quiz.Question;

import java.util.UUID;

public interface QuestionRepository extends CrudRepository<Question, UUID> {

}
