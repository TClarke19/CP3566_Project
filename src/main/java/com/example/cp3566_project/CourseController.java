package com.example.cp3566_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseRepository repository;

    @PostMapping("/api/addCourse")
    public Course addCourse(@RequestBody Course course) {
        return repository.save(course);
    }

    @GetMapping("/api/getCourses")
    public Iterable<Course> getAllCourses() {
        return repository.findAll();
    }

    @GetMapping("/api/getCourses/{id}")
    public Optional<Course> getCourseById(@PathVariable Long id) {
        return repository.findById(id);
    }
}