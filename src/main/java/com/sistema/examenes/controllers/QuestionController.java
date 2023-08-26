package com.sistema.examenes.controllers;

import com.sistema.examenes.entities.Exam;
import com.sistema.examenes.entities.Question;
import com.sistema.examenes.repositories.ExamRepository;
import com.sistema.examenes.services.impl.ExamServiceImpl;
import com.sistema.examenes.services.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private ExamServiceImpl examService;

    @PostMapping("/")
    public ResponseEntity<Question> saveQuestion(@RequestBody Question question){
        return ResponseEntity.ok(questionService.addQuestion(question));
    }

    @PostMapping("/evaluate-exam")
    public ResponseEntity<?> evaluateExam(@RequestBody List<Question> questions){

        double maxPoints = 0;
        Integer correctQuestion = 0;
        Integer attempts = 0;

        for(Question q: questions){
            Question question = questionService.listQuestion(q.getId());
            if(question.getAnswer().equals(q.getGivenAnswer())){
                correctQuestion ++;
                double points = Double.parseDouble(questions.get(0).getExam().getMaxPoints())/questions.size();
                maxPoints ++;
            }
            if(q.getGivenAnswer() != null) attempts++;
        }

        Map<String, Object> answers = new HashMap<>();
        answers.put("maxPoints", maxPoints);
        answers.put("correctQuestion", correctQuestion);
        answers.put("attempts", attempts);

        return  ResponseEntity.ok(answers);
    }

    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        return ResponseEntity.ok(questionService.updateQuestion(question));
    }

    @GetMapping("/exam/{examId}")
    public ResponseEntity<?> listQuestionOfExam(@PathVariable("examId") Long examId){
        Exam exam = examService.getOnlyExam(examId);
        Set<Question> questions = exam.getQuestions();

        List questionExam = new ArrayList(questions);

        if(questionExam.size() > Integer.parseInt(exam.getQuestionNumber())){
            questionExam = questionExam.subList(0, Integer.parseInt(exam.getQuestionNumber()));
        }

        Collections.shuffle(questionExam); //se mezclan las preguntas, en cada solicitud aparecen en un orden aleatorio
        return ResponseEntity.ok(questionExam);
    }

    @GetMapping("/exam/all/{examId}")
    public ResponseEntity<?> listExamQuestionAsAdministrator(@PathVariable("examId") Long examId){
        Exam exam = new Exam();
        exam.setId(examId);
        Set<Question> questions = questionService.getQuestionForExam(exam);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{questionId}")
    public Question getQuestion(@PathVariable("questionId") Long questionId){
        return questionService.getOnlyQuestion(questionId);
    }

    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Long questionId){
        questionService.deleteQuestion(questionId);
    }

}
