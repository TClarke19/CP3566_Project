package com.example.cp3566_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/program")
public class ProgramsController {

    @Autowired
    private ProgramsRepository repository;

    @PostMapping("/add")
    public ResponseEntity<Programs> addProgram(@RequestBody Programs program) {
        Programs savedProgram = repository.save(program);
        return ResponseEntity.ok(savedProgram);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Iterable<Programs>> getAllPrograms() {
        Iterable<Programs> programs = repository.findAll();
        return ResponseEntity.ok(programs);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Programs>> getProgramById(@PathVariable Long id) {
        Optional<Programs> program = repository.findById(id);
        return ResponseEntity.of(Optional.of(program));
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<Programs> modifyProgram(@PathVariable Long id, @RequestBody Programs program) {
        Optional<Programs> optionalProgram = repository.findById(id);
        if (optionalProgram.isPresent()) {
            Programs existingProgram = optionalProgram.get();
            existingProgram.setProgramName(program.getProgramName());
            existingProgram.setCampus(program.getCampus());
            repository.save(existingProgram);
            return ResponseEntity.ok(existingProgram);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}