package com.hardteach.school.repositories;

import com.hardteach.school.entities.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, String> {
    Asignatura findByNombre(String nombreAsignatura);
}
