package com.college.evaluation.repository;

import com.college.evaluation.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    Optional<Student> findByRegistrationNumber(String registrationNumber);
    boolean existsByEmail(String email);
}
