package com.college.evaluation.service;

import com.college.evaluation.exception.ResourceNotFoundException;
import com.college.evaluation.model.Course;
import com.college.evaluation.model.Evaluation;
import com.college.evaluation.model.Student;
import com.college.evaluation.repository.CourseRepository;
import com.college.evaluation.repository.EvaluationRepository;
import com.college.evaluation.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Transactional(readOnly = true)
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Evaluation getEvaluationById(Long id) {
        return evaluationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation not found with id: " + id));
    }

    @Transactional
    public Evaluation createEvaluation(Evaluation evaluation) {
        // Verify student exists
        if (evaluation.getStudent() != null && evaluation.getStudent().getId() != null) {
            Student student = studentRepository.findById(evaluation.getStudent().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Student not found with id: " + evaluation.getStudent().getId()));
            evaluation.setStudent(student);
        }
        
        // Verify course exists
        if (evaluation.getCourse() != null && evaluation.getCourse().getId() != null) {
            Course course = courseRepository.findById(evaluation.getCourse().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Course not found with id: " + evaluation.getCourse().getId()));
            evaluation.setCourse(course);
        }
        
        return evaluationRepository.save(evaluation);
    }

    @Transactional
    public Evaluation updateEvaluation(Long id, Evaluation evaluationDetails) {
        Evaluation evaluation = getEvaluationById(id);
        
        evaluation.setGrade(evaluationDetails.getGrade());
        evaluation.setComments(evaluationDetails.getComments());
        evaluation.setEvaluationType(evaluationDetails.getEvaluationType());
        
        return evaluationRepository.save(evaluation);
    }

    @Transactional
    public void deleteEvaluation(Long id) {
        Evaluation evaluation = getEvaluationById(id);
        evaluationRepository.delete(evaluation);
    }

    @Transactional(readOnly = true)
    public List<Evaluation> getEvaluationsByStudentId(Long studentId) {
        return evaluationRepository.findByStudentId(studentId);
    }

    @Transactional(readOnly = true)
    public List<Evaluation> getEvaluationsByCourseId(Long courseId) {
        return evaluationRepository.findByCourseId(courseId);
    }
}
