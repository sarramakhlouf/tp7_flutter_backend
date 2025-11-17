package com.tp7.flutter.entities;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceId implements Serializable {
    private Long codMat;
    private Long etudiantId;
    private Date dateA;
}