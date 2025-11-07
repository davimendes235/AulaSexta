package com.college.evaluation.service;

import com.college.evaluation.exception.DuplicateResourceException;
import com.college.evaluation.exception.ResourceNotFoundException;
import com.college.evaluation.model.Student;
import com.college.evaluation.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    @Transactional
    public Student createStudent(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new DuplicateResourceException("Student already exists with email: " + student.getEmail());
        }
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = getStudentById(id);
        
        if (!student.getEmail().equals(studentDetails.getEmail()) 
                && studentRepository.existsByEmail(studentDetails.getEmail())) {
            throw new DuplicateResourceException("Email already in use: " + studentDetails.getEmail());
        }
        
        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setRegistrationNumber(studentDetails.getRegistrationNumber());
        
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }
}
