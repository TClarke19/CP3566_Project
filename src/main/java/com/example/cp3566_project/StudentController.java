package com.example.cp3566_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/student")
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @PostMapping(path="/api/addStudent")
    public Student addStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    @GetMapping(path="/api/getStudents")
    public Iterable<Student> getAllStudents() {
        return repository.findAll();
    }

    @GetMapping(path="/api/getStudents/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id) {
        return repository.findById(id);
    }
}
