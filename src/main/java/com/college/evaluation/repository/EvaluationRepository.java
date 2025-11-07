package com.college.evaluation.repository;

import com.college.evaluation.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findByStudentId(Long studentId);
    List<Evaluation> findByCourseId(Long courseId);
    List<Evaluation> findByStudentIdAndCourseId(Long studentId, Long courseId);
}
