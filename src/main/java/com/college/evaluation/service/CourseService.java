package com.college.evaluation.service;

import com.college.evaluation.exception.DuplicateResourceException;
import com.college.evaluation.exception.ResourceNotFoundException;
import com.college.evaluation.model.Course;
import com.college.evaluation.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    @Transactional(readOnly = true)
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    @Transactional
    public Course createCourse(Course course) {
        if (course.getCourseCode() != null && courseRepository.existsByCourseCode(course.getCourseCode())) {
            throw new DuplicateResourceException("Course already exists with code: " + course.getCourseCode());
        }
        return courseRepository.save(course);
    }

    @Transactional
    public Course updateCourse(Long id, Course courseDetails) {
        Course course = getCourseById(id);
        
        if (courseDetails.getCourseCode() != null 
                && !courseDetails.getCourseCode().equals(course.getCourseCode())
                && courseRepository.existsByCourseCode(courseDetails.getCourseCode())) {
            throw new DuplicateResourceException("Course code already in use: " + courseDetails.getCourseCode());
        }
        
        course.setName(courseDetails.getName());
        course.setCourseCode(courseDetails.getCourseCode());
        course.setDescription(courseDetails.getDescription());
        course.setCredits(courseDetails.getCredits());
        course.setProfessor(courseDetails.getProfessor());
        
        return courseRepository.save(course);
    }

    @Transactional
    public void deleteCourse(Long id) {
        Course course = getCourseById(id);
        courseRepository.delete(course);
    }

    @Transactional(readOnly = true)
    public List<Course> getCoursesByProfessorId(Long professorId) {
        return courseRepository.findByProfessorId(professorId);
    }
}
