package com.example.cp3566_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentRepository repository;

    @PostMapping("/add")
    public ResponseEntity<Enrollment> addEnrollment(@RequestBody Enrollment enrollment) {
        Enrollment savedEnrollment = repository.save(enrollment);
        return ResponseEntity.ok(savedEnrollment);
    }

    @GetMapping("/getAllByCourse/{courseId}")
    public ResponseEntity<Iterable<Enrollment>> getAllEnrollmentsByCourse(@PathVariable Long courseId) {
        Iterable<Enrollment> enrollments = repository.findAllById(Collections.singleton(courseId));
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/getAllByStudent/{studentId}")
    public ResponseEntity<Iterable<Enrollment>> getAllEnrollmentsByStudent(@PathVariable Long studentId) {
        Iterable<Enrollment> enrollments = repository.findAllById(Collections.singleton(studentId));
        return ResponseEntity.ok(enrollments);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<Enrollment> modifyEnrollment(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        Optional<Enrollment> optionalEnrollment = repository.findById(id);
        if (optionalEnrollment.isPresent()) {
            Enrollment existingEnrollment = optionalEnrollment.get();
            existingEnrollment.setCourse(enrollment.getCourse());
            existingEnrollment.setStudent(enrollment.getStudent());
            repository.save(existingEnrollment);
            return ResponseEntity.ok(existingEnrollment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}