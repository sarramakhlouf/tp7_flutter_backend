package com.tp7.flutter.restControllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import com.tp7.flutter.entities.Etudiant;
import com.tp7.flutter.entities.Classe;
import com.tp7.flutter.repos.EtudiantRepository;
import com.tp7.flutter.repos.ClasseRepository;

@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin(origins = "*")
public class EtudiantController {

    @Autowired
    private EtudiantRepository etudiantRepo;

    @Autowired
    private ClasseRepository classeRepo;

    @GetMapping
    public List<Etudiant> all() {
        return etudiantRepo.findAll();
    }

    @GetMapping("/classe/{id}")
    public List<Etudiant> byClasse(@PathVariable Long id) {
        return etudiantRepo.findByClasse_Id(id);
    }

    @PostMapping("/add")
    public Etudiant add(@RequestBody Etudiant e) {
        if (e.getClasse() != null) {
            Classe c = classeRepo.findById(e.getClasse().getId())
                    .orElseThrow(() -> new RuntimeException("Classe non trouvée"));
            e.setClasse(c);
        }
        return etudiantRepo.save(e);
    }
    
    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        etudiantRepo.deleteById(id);
    }

    @PutMapping("edit/{id}")
    public Etudiant update(@PathVariable Long id, @RequestBody Etudiant updatedEtudiant) {
        Etudiant etudiant = etudiantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
        
        etudiant.setNom(updatedEtudiant.getNom());
        etudiant.setPrenom(updatedEtudiant.getPrenom());
        etudiant.setDateNais(updatedEtudiant.getDateNais());
        etudiant.setLieuNais(updatedEtudiant.getLieuNais());
        
        if (updatedEtudiant.getClasse() != null) {
            Classe c = classeRepo.findById(updatedEtudiant.getClasse().getId())
                    .orElseThrow(() -> new RuntimeException("Classe non trouvée"));
            etudiant.setClasse(c);
        }

        return etudiantRepo.save(etudiant);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        return etudiantRepo.findById(id)
                .map(etudiant -> ResponseEntity.ok(etudiant))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
