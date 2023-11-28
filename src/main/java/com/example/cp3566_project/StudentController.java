package com.example.cp3566_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/api/student")
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @PostMapping(path="/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student savedStudent = repository.save(student);
        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping(path="/getAll")
    public ResponseEntity<Iterable<Student>> getAllStudents() {
        Iterable<Student> students = repository.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping(path="/get/{id}")
    public ResponseEntity<Optional<Student>> getStudentById(@PathVariable Long id) {
        Optional<Student> student = repository.findById(id);
        return ResponseEntity.of(Optional.of(student));
    }
}
