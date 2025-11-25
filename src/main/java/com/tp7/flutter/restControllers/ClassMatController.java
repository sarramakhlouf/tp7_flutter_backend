package com.tp7.flutter.restControllers;

import com.tp7.flutter.entities.ClassMat;
import com.tp7.flutter.entities.ClassMatId;
import com.tp7.flutter.repos.ClassMatRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classmat")
@RequiredArgsConstructor
public class ClassMatController {

    private final ClassMatRepository repository;

    @PostMapping
    public ClassMat create(@RequestBody ClassMat classMat) {
        return repository.save(classMat);
    }

    @PutMapping
    public ClassMat update(@RequestBody ClassMat classMat) {
        return repository.save(classMat);
    }

    @DeleteMapping("/{codMat}/{codClass}")
    public void delete(@PathVariable Long codMat, @PathVariable Long codClass) {
        ClassMatId id = new ClassMatId(codMat, codClass);
        repository.deleteById(id);
    }

    @GetMapping("/{codMat}/{codClass}")
    public ClassMat getById(@PathVariable Long codMat, @PathVariable Long codClass) {
        ClassMatId id = new ClassMatId(codMat, codClass);
        return repository.findById(id).orElse(null);
    }

    @GetMapping
    public List<ClassMat> getAll() {
        return repository.findAll();
    }
}

