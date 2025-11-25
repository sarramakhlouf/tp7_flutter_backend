package com.tp7.flutter.restControllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tp7.flutter.entities.Absence;
import com.tp7.flutter.entities.AbsenceId;
import com.tp7.flutter.repos.AbsenceRepository;

@RestController
@RequestMapping("/api/absences")
@CrossOrigin(origins = "*")
public class AbsenceController {
    @Autowired
    private AbsenceRepository absenceRepo;

    @GetMapping
    public List<Absence> getAll() {
        return absenceRepo.findAll();
    }
    
    // GET absences par étudiant
    @GetMapping("/etudiant/{etudiantId}")
    public List<Absence> getByEtudiant(@PathVariable Long etudiantId) {
        return absenceRepo.findByEtudiantId(etudiantId);
    }

    @PostMapping("/add")
    public Absence insert(@RequestBody Absence a) {
        return absenceRepo.save(a);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable AbsenceId id) {
        absenceRepo.deleteById(id);
    }
    
    @GetMapping("/total/{etudiantId}")
    public List<Map<String, Object>> getTotalAbsencesByEtudiant(
            @PathVariable("etudiantId") Long etudiantId) {

        List<Absence> absences = absenceRepo.findByEtudiantId(etudiantId);

        // Calculer le total des NHA par matière
        Map<String, Double> totalsMap = new HashMap<>();
        for (Absence a : absences) {
        	String codMatStr = String.valueOf(a.getCodMat());
        	totalsMap.put(codMatStr, totalsMap.getOrDefault(codMatStr, 0.0) + a.getNha());
        }

        // Convertir en liste de maps pour JSON
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<String, Double> entry : totalsMap.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("codMat", entry.getKey());
            map.put("totalNha", entry.getValue());
            result.add(map);
        }

        return result;
    }
}
