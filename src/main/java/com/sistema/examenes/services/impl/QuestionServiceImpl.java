package com.sistema.examenes.services.impl;

import com.sistema.examenes.entities.Exam;
import com.sistema.examenes.entities.Question;
import com.sistema.examenes.repositories.QuestionRepository;
import com.sistema.examenes.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Set<Question> getQuestions() {
        return (Set<Question>) questionRepository.findAll();
    }

    @Override
    public Question getOnlyQuestion(Long questionId) {
        return questionRepository.findById(questionId).get();
    }

    @Override
    public Set<Question> getQuestionForExam(Exam exam) {
        return questionRepository.findByExam(exam);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        Question question = new Question();
        question.setId(questionId);
        questionRepository.delete(question);
    }

    @Override
    public Question listQuestion(Long questionId) {
        return questionRepository.getOne(questionId);
    }
}
