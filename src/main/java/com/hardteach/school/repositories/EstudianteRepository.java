package com.hardteach.school.repositories;

import com.hardteach.school.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Estudiante findByIdentificacion(String identificacion);
}
