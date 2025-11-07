package com.college.evaluation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "evaluations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Grade is required")
    @Min(value = 0, message = "Grade must be at least 0")
    @Max(value = 100, message = "Grade must be at most 100")
    @Column(nullable = false)
    private Double grade;

    @Column(name = "evaluation_date")
    private LocalDateTime evaluationDate;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Enumerated(EnumType.STRING)
    @Column(name = "evaluation_type")
    private EvaluationType evaluationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @PrePersist
    protected void onCreate() {
        if (evaluationDate == null) {
            evaluationDate = LocalDateTime.now();
        }
    }

    public enum EvaluationType {
        EXAM,
        QUIZ,
        ASSIGNMENT,
        PROJECT,
        MIDTERM,
        FINAL
    }
}
