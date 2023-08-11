package com.sistema.examenes.repositories;

import com.sistema.examenes.entities.Exam;
import com.sistema.examenes.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Set<Question> findByExam(Exam exam);

}
