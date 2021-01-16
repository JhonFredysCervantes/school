package com.hardteach.school.services;

import com.hardteach.school.entities.Asignatura;

import java.util.List;
import java.util.Optional;

public interface AsignaturaService {
    Asignatura crearAsignatura(Asignatura asig);
    Asignatura actualizarAsignatura(Asignatura asig);
    Optional<Asignatura> buscarAsignatura(Long id);
    List<Asignatura> buscarAsignaturas();
    void eliminarAsignatura(Long id);
}
