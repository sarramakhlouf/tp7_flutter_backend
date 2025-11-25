package com.tp7.flutter.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tp7.flutter.entities.ClassMat;
import com.tp7.flutter.entities.ClassMatId;

public interface ClassMatRepository extends JpaRepository<ClassMat, ClassMatId> {
}
