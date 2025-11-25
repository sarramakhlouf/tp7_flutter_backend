package com.tp7.flutter.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteId implements Serializable {
    private Long etudiantId;
    private Long codMat;
}
