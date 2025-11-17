package com.tp7.flutter.restControllers;

import com.tp7.flutter.entities.Matiere;
import com.tp7.flutter.repos.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matieres")
@CrossOrigin(origins = "*") 
public class MatiereController {

    @Autowired
    private MatiereRepository matiereRepository;


    @GetMapping
    public List<Matiere> getAllMatieres() {
        return matiereRepository.findAll();
    }

    @GetMapping("/{id}")
    public Matiere getMatiereById(@PathVariable Long id) {
        return matiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matiere non trouvée avec id : " + id));
    }

    @PostMapping("/add")
    public Matiere createMatiere(@RequestBody Matiere matiere) {
        return matiereRepository.save(matiere);
    }
    
    @PutMapping("/edit/{id}")
    public Matiere updateMatiere(@PathVariable Long id, @RequestBody Matiere matiereDetails) {
        Matiere matiere = matiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matiere non trouvée avec id : " + id));

        matiere.setIntMat(matiereDetails.getIntMat());
        matiere.setDescription(matiereDetails.getDescription());

        return matiereRepository.save(matiere);
    }

    @DeleteMapping("/{id}")
    public String deleteMatiere(@PathVariable Long id) {
        matiereRepository.deleteById(id);
        return "Matiere supprimée avec succès avec id : " + id;
    }
}
