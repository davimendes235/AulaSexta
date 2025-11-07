# College Evaluation System

A Spring Boot application for managing college evaluations, courses, students, and professors.

## Features

- **Student Management**: Create, read, update, and delete student records
- **Professor Management**: Manage professor information and departments
- **Course Management**: Handle course details, credits, and assignments
- **Evaluation System**: Track student evaluations across different courses with multiple evaluation types (Exam, Quiz, Assignment, Project, Midterm, Final)

## Technology Stack

- **Java 17**
- **Spring Boot 3.1.5**
- **Spring Data JPA**
- **H2 Database** (in-memory for development)
- **Maven** (build tool)
- **Lombok** (to reduce boilerplate code)

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Getting Started

### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Access H2 Console

You can access the H2 database console at: `http://localhost:8080/h2-console`

- JDBC URL: `jdbc:h2:mem:collegedb`
- Username: `sa`
- Password: (leave empty)

## API Endpoints

### Students API

- `GET /api/students` - Get all students
- `GET /api/students/{id}` - Get student by ID
- `POST /api/students` - Create a new student
- `PUT /api/students/{id}` - Update student
- `DELETE /api/students/{id}` - Delete student

### Professors API

- `GET /api/professors` - Get all professors
- `GET /api/professors/{id}` - Get professor by ID
- `POST /api/professors` - Create a new professor
- `PUT /api/professors/{id}` - Update professor
- `DELETE /api/professors/{id}` - Delete professor

### Courses API

- `GET /api/courses` - Get all courses
- `GET /api/courses/{id}` - Get course by ID
- `GET /api/courses/professor/{professorId}` - Get courses by professor
- `POST /api/courses` - Create a new course
- `PUT /api/courses/{id}` - Update course
- `DELETE /api/courses/{id}` - Delete course

### Evaluations API

- `GET /api/evaluations` - Get all evaluations
- `GET /api/evaluations/{id}` - Get evaluation by ID
- `GET /api/evaluations/student/{studentId}` - Get evaluations by student
- `GET /api/evaluations/course/{courseId}` - Get evaluations by course
- `POST /api/evaluations` - Create a new evaluation
- `PUT /api/evaluations/{id}` - Update evaluation
- `DELETE /api/evaluations/{id}` - Delete evaluation

## Example Requests

### Create a Student

```json
POST /api/students
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "registrationNumber": "STU001"
}
```

### Create a Professor

```json
POST /api/professors
{
  "name": "Dr. Jane Smith",
  "email": "jane.smith@example.com",
  "department": "Computer Science"
}
```

### Create a Course

```json
POST /api/courses
{
  "name": "Introduction to Programming",
  "courseCode": "CS101",
  "description": "Basic programming concepts",
  "credits": 3,
  "professor": {
    "id": 1
  }
}
```

### Create an Evaluation

```json
POST /api/evaluations
{
  "grade": 85.5,
  "comments": "Good performance",
  "evaluationType": "EXAM",
  "student": {
    "id": 1
  },
  "course": {
    "id": 1
  }
}
```

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/college/evaluation/
│   │       ├── controller/      # REST Controllers
│   │       ├── model/           # Entity classes
│   │       ├── repository/      # JPA Repositories
│   │       ├── service/         # Business logic
│   │       ├── exception/       # Custom exceptions and handlers
│   │       └── CollegeEvaluationApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/college/evaluation/
```

## License

This project is open source and available under the MIT License.