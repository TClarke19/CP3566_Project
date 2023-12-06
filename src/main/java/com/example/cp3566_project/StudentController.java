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

    @PutMapping(path="/modify/{id}")
    public ResponseEntity<Student> modifyStudent(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> optionalStudent = repository.findById(id);
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setFirstName(student.getFirstName());
            existingStudent.setLastName(student.getLastName());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setAddress(student.getAddress());
            existingStudent.setCity(student.getCity());
            existingStudent.setPostal(student.getPostal());
            existingStudent.setPhone(student.getPhone());
            repository.save(existingStudent);
            return ResponseEntity.ok(existingStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
