package com.college.evaluation.repository;

import com.college.evaluation.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByEmail(String email);
    List<Professor> findByDepartment(String department);
    boolean existsByEmail(String email);
}
