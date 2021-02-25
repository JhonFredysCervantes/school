package com.hardtech.school.services;

import com.hardtech.school.entities.Docente;

import java.util.List;
import java.util.Optional;

public interface IDocenteService {
    Docente crear(Docente docente);
    Docente actualizar(Docente docente);
    Optional buscarPorId(Long id);
    Docente buscarPorIdentificacion(String identificacion);
    List<Docente> buscarDocentes();
    void eliminar(Long id);
}
