package com.sistema.examenes.repositories;

import com.sistema.examenes.entities.Category;
import com.sistema.examenes.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    //buscará examenes por categoria
    List<Exam> findByCategory(Category category);

    //listar examenes con estado activo
    List<Exam> findByActive(Boolean active);

    //listar examenes activos por categoria
    List<Exam> findByCategoryAndActive(Category category, Boolean active);
}
