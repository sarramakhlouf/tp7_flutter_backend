package com.tp7.flutter.entities;

import java.sql.Date;

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
@IdClass(AbsenceId.class)
public class Absence {
    @Id
    private Long codMat;

    @Id
    private Long etudiantId;

    @Id
    private Date dateA;

    private Integer nha;
}
