package com.tp7.flutter.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(NoteId.class)
public class Note {

    @Id
    private Long etudiantId;

    @Id
    private Long codMat;

    private Double valeurNote;
}
