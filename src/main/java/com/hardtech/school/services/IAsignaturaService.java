package com.hardtech.school.services;

import com.hardtech.school.entities.Asignatura;

import java.util.List;
import java.util.Optional;

public interface IAsignaturaService {
    Asignatura crearAsignatura(Asignatura asig);
    Asignatura actualizarAsignatura(Asignatura asig);
    Optional<Asignatura> buscarAsignatura(String id);
    List<Asignatura> buscarAsignaturas();
    void eliminarAsignatura(String id);
}
