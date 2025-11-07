package com.college.evaluation.service;

import com.college.evaluation.exception.DuplicateResourceException;
import com.college.evaluation.exception.ResourceNotFoundException;
import com.college.evaluation.model.Professor;
import com.college.evaluation.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    @Transactional(readOnly = true)
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Professor getProfessorById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found with id: " + id));
    }

    @Transactional
    public Professor createProfessor(Professor professor) {
        if (professorRepository.existsByEmail(professor.getEmail())) {
            throw new DuplicateResourceException("Professor already exists with email: " + professor.getEmail());
        }
        return professorRepository.save(professor);
    }

    @Transactional
    public Professor updateProfessor(Long id, Professor professorDetails) {
        Professor professor = getProfessorById(id);
        
        if (!professor.getEmail().equals(professorDetails.getEmail()) 
                && professorRepository.existsByEmail(professorDetails.getEmail())) {
            throw new DuplicateResourceException("Email already in use: " + professorDetails.getEmail());
        }
        
        professor.setName(professorDetails.getName());
        professor.setEmail(professorDetails.getEmail());
        professor.setDepartment(professorDetails.getDepartment());
        
        return professorRepository.save(professor);
    }

    @Transactional
    public void deleteProfessor(Long id) {
        Professor professor = getProfessorById(id);
        professorRepository.delete(professor);
    }
}
