package com.hardtech.school.repositories;

import com.hardtech.school.entities.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository extends JpaRepository<Docente,Long> {
    Docente findByIdentificacion(String identificacion);
}
