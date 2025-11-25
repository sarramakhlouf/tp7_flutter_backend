package com.tp7.flutter.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tp7.flutter.entities.Classe;
import com.tp7.flutter.entities.Etudiant;

public interface ClasseRepository extends JpaRepository<Classe, Long> {
	List<Classe> findByDepartement_Id(Long codDept);
}
