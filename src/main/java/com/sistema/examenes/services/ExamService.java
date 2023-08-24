package com.sistema.examenes.services;

import com.sistema.examenes.entities.Category;
import com.sistema.examenes.entities.Exam;

import java.util.List;
import java.util.Set;

public interface ExamService {

    Exam createExam(Exam exam);

    Exam updateExam(Exam exam);

    Set<Exam> getExams();

    Exam getOnlyExam(Long examId);

    void deleteExam(Long examId);

    List<Exam> listExamByCategory(Category categoryId);

    List<Exam> getExamsActive();

    List<Exam> getExamsActiveByCategory(Category category);

}
