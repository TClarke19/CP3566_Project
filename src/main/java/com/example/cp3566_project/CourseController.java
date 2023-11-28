package com.example.cp3566_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseRepository repository;

    @PostMapping("/add")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course savedCourse = repository.save(course);
        return ResponseEntity.ok(savedCourse);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Iterable<Course>> getAllCourses() {
        Iterable<Course> courses = repository.findAll();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Course>> getCourseById(@PathVariable Long id) {
        Optional<Course> course = repository.findById(id);
        return ResponseEntity.of(Optional.of(course));
    }
}