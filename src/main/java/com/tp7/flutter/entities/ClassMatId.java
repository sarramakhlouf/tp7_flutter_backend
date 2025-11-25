package com.tp7.flutter.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassMatId implements Serializable {
    private Long codMat;
    private Long codClass;
}

