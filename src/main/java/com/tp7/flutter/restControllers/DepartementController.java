package com.tp7.flutter.restControllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tp7.flutter.entities.Departement;
import com.tp7.flutter.repos.DepartementRepository;

@RestController
@RequestMapping("/api/departements")
@CrossOrigin("*")
public class DepartementController {

    private final DepartementRepository repo;

    public DepartementController(DepartementRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Departement> getAll() {
        return repo.findAll();
    }

    @PostMapping("/add")
    public Departement create(@RequestBody Departement d) {
        return repo.save(d);
    }

    @GetMapping("/{id}")
    public Departement getOne(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }
    
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Departement introuvable !");
        }
        repo.deleteById(id);
    }
}