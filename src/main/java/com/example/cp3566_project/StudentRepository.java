package com.example.cp3566_project;

import org.springframework.data.repository.CrudRepository;

import com.example.cp3566_project.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
