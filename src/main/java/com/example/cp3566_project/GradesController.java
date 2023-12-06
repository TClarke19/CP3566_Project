package com.example.cp3566_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/grades")
public class GradesController {

    @Autowired
    private GradesRepository repository;

    @PostMapping("/add")
    public ResponseEntity<Grades> addGrade(@RequestBody Grades grade) {
        Grades savedGrade = repository.save(grade);
        return ResponseEntity.ok(savedGrade);
    }

    @GetMapping("/getAllByStudent/{studentId}")
    public ResponseEntity<Iterable<Grades>> getAllGradesByStudent(@PathVariable Long studentId) {
        Iterable<Grades> grades = repository.findAllById(Collections.singleton(studentId));
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/getAllByCourse/{courseId}")
    public ResponseEntity<Iterable<Grades>> getAllGradesByCourse(@PathVariable Long courseId) {
        Iterable<Grades> grades = repository.findAllById(Collections.singleton(courseId));
        return ResponseEntity.ok(grades);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<Grades> modifyGrade(@PathVariable Long id, @RequestBody Grades grade) {
        Optional<Grades> optionalGrade = repository.findById(id);
        if (optionalGrade.isPresent()) {
            Grades existingGrade = optionalGrade.get();
            existingGrade.setCourse(grade.getCourse());
            existingGrade.setStudent(grade.getStudent());
            existingGrade.setGrade(grade.getGrade());
            repository.save(existingGrade);
            return ResponseEntity.ok(existingGrade);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}