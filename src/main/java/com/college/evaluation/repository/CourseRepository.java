package com.college.evaluation.repository;

import com.college.evaluation.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCourseCode(String courseCode);
    List<Course> findByProfessorId(Long professorId);
    boolean existsByCourseCode(String courseCode);
}
