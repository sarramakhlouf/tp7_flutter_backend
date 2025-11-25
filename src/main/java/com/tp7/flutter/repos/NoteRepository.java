package com.tp7.flutter.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tp7.flutter.entities.Note;
import com.tp7.flutter.entities.NoteId;

public interface NoteRepository extends JpaRepository<Note, NoteId> {
	List<Note> findByEtudiantId(Long etudiantId);
	Note findByEtudiantIdAndCodMat(Long etudiantId, Long codMat);

}
