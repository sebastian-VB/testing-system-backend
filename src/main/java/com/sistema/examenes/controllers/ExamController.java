package com.sistema.examenes.controllers;


import com.sistema.examenes.entities.Exam;
import com.sistema.examenes.services.impl.ExamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam")
@CrossOrigin("*")
public class ExamController {

    @Autowired
    private ExamServiceImpl examService;

    @PostMapping("/")
    public ResponseEntity<Exam> saveCategory(@RequestBody Exam exam){
        Exam savedExam = examService.createExam(exam);
        return ResponseEntity.ok(savedExam);
    }

    @GetMapping("/{examId}")
    public Exam listExamById(@PathVariable("examId") Long examId){
        return examService.getOnlyExam(examId);
    }

    @GetMapping("/")
    public ResponseEntity<?> listExams(){
        return ResponseEntity.ok(examService.getExams());
    }

    @PutMapping("/")
    public Exam updateExam(@RequestBody Exam exam){
        return examService.updateExam(exam);
    }

    @DeleteMapping("/{examId}")
    public void deleteExam(@PathVariable("examId") Long examId){
        examService.deleteExam(examId);
    }
}
