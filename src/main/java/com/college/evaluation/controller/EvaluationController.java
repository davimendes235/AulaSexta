package com.college.evaluation.controller;

import com.college.evaluation.model.Evaluation;
import com.college.evaluation.service.EvaluationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;

    @GetMapping
    public ResponseEntity<List<Evaluation>> getAllEvaluations() {
        return ResponseEntity.ok(evaluationService.getAllEvaluations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluation> getEvaluationById(@PathVariable Long id) {
        return ResponseEntity.ok(evaluationService.getEvaluationById(id));
    }

    @PostMapping
    public ResponseEntity<Evaluation> createEvaluation(@Valid @RequestBody Evaluation evaluation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(evaluationService.createEvaluation(evaluation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evaluation> updateEvaluation(@PathVariable Long id, @Valid @RequestBody Evaluation evaluation) {
        return ResponseEntity.ok(evaluationService.updateEvaluation(id, evaluation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id) {
        evaluationService.deleteEvaluation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Evaluation>> getEvaluationsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(evaluationService.getEvaluationsByStudentId(studentId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Evaluation>> getEvaluationsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(evaluationService.getEvaluationsByCourseId(courseId));
    }
}
