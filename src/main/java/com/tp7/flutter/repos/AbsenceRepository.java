package com.tp7.flutter.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tp7.flutter.entities.Absence;
import com.tp7.flutter.entities.AbsenceId;

public interface AbsenceRepository extends JpaRepository<Absence, AbsenceId> {
	List<Absence> findByEtudiantId(Long etudiantId);
}
