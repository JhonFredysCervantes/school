package com.hardteach.school.repositories;

import com.hardteach.school.entities.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository extends JpaRepository<Docente,Long> {
    Docente findByIdentificacion(String identificacion);
}
