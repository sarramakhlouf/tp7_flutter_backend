package com.tp7.flutter.restControllers;

import com.tp7.flutter.entities.Note;
import com.tp7.flutter.entities.NoteId;
import com.tp7.flutter.repos.NoteRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteRepository repository;

    @PostMapping
    public Note create(@RequestBody Note note) {
        return repository.save(note);
    }

    @PutMapping("/{etudiantId}/{codMat}")
    public Note updateNote(
            @PathVariable Long etudiantId,
            @PathVariable Long codMat,
            @RequestBody Note newNote
    ) {
        Note existing = repository.findByEtudiantIdAndCodMat(etudiantId, codMat);

        if (existing == null) {
            throw new RuntimeException("Note introuvable");
        }

        existing.setValeurNote(newNote.getValeurNote());

        return repository.save(existing);
    }


    @DeleteMapping("/{etudiantId}/{codMat}")
    public void delete(@PathVariable Long etudiantId, @PathVariable Long codMat) {
        NoteId id = new NoteId(etudiantId, codMat);
        repository.deleteById(id);
    }

    @GetMapping("/{etudiantId}/{codMat}")
    public Note getById(@PathVariable Long etudiantId, @PathVariable Long codMat) {
        NoteId id = new NoteId(etudiantId, codMat);
        return repository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Note> getAll() {
        return repository.findAll();
    }
    
    @GetMapping("/etudiant/{etudiantId}")
    public List<Note> getNotesByEtudiant(@PathVariable Long etudiantId) {
        return repository.findByEtudiantId(etudiantId);
    }
    

}
