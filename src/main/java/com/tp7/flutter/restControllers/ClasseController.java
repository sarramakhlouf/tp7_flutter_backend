package com.tp7.flutter.restControllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;


import com.tp7.flutter.entities.Classe;
import com.tp7.flutter.entities.Departement;
import com.tp7.flutter.entities.Etudiant;
import com.tp7.flutter.repos.ClasseRepository;
import com.tp7.flutter.repos.DepartementRepository;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "*")
public class ClasseController {

    @Autowired
    private ClasseRepository repo;
    
    @Autowired
    private DepartementRepository deptRepo;

    @GetMapping
    public List<Classe> all() {
        return repo.findAll();
    }
    
    @GetMapping("/dep/{codDept}")
    public List<Classe> byDepartement(@PathVariable Long codDept) {
        return repo.findByDepartement_Id(codDept);
    }
    
    @PostMapping("/add")
    public Classe add(@RequestBody Classe c) {
        if (c.getDepartement() != null) {
            Departement d = deptRepo.findById(c.getDepartement().getId())
                    .orElseThrow(() -> new RuntimeException("Département non trouvé"));
            c.setDepartement(d);
        }
        return repo.save(c);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Classe introuvable !");
        }

        repo.deleteById(id); // supprime aussi les étudiants
        return ResponseEntity.ok("Classe supprimée");
    }


}
