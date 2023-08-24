package com.sistema.examenes.services.impl;

import com.sistema.examenes.entities.Category;
import com.sistema.examenes.entities.Exam;
import com.sistema.examenes.repositories.ExamRepository;
import com.sistema.examenes.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public Exam updateExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public Set<Exam> getExams() {
        return new LinkedHashSet<Exam>(examRepository.findAll());
    }

    @Override
    public Exam getOnlyExam(Long examId) {
        return examRepository.findById(examId).get();
    }

    @Override
    public void deleteExam(Long examId) {
        Exam exam = new Exam();
        exam.setId(examId);
        examRepository.delete(exam);
    }

    @Override
    public List<Exam> listExamByCategory(Category category) {
        return examRepository.findByCategory(category);
    }

    @Override
    public List<Exam> getExamsActive() {
        return examRepository.findByActive(true);
    }

    @Override
    public List<Exam> getExamsActiveByCategory(Category category) {
        return examRepository.findByCategoryAndActive(category, true);
    }
}
