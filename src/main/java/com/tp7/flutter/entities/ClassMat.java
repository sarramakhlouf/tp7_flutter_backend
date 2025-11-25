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
@IdClass(ClassMatId.class)
public class ClassMat {

    @Id
    private Long codMat;

    @Id
    private Long codClass;

    private Integer coef;
    private Integer chsm;
}
