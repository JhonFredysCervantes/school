package com.hardteach.school.services;

import com.hardteach.school.entities.Estudiante;

import java.util.List;
import java.util.Optional;

public interface EstudianteService {
    Estudiante crear(Estudiante estudiante);
    Estudiante actualizar(Estudiante estudiante);
    Optional buscarPorId(Long id);
    Estudiante buscarPorIdentificacion(String identificacion);
    List<Estudiante> buscarEstudiantes();
    void eliminar(Long id);
}
