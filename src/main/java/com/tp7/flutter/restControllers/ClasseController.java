package com.tp7.flutter.restControllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.tp7.flutter.entities.Classe;
import com.tp7.flutter.repos.ClasseRepository;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "*")
public class ClasseController {

    @Autowired
    private ClasseRepository repo;

    @GetMapping
    public List<Classe> all() {
        return repo.findAll();
    }

    @PostMapping("/add")
    public Classe add(@RequestBody Classe c) {
        if (c.getNomClass() == null || c.getNomClass().isEmpty()) {
            throw new RuntimeException("Le nom de la classe est obligatoire");
        }
        return repo.save(c);
    }
    
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Classe introuvable !");
        }
        repo.deleteById(id);
    }

}
