package com.hardtech.school.services;

import com.hardtech.school.entities.Estudiante;
import com.hardtech.school.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class EstudianteServiceImp implements IEstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepo;

    @Override
    public Estudiante crear(Estudiante estudiante) {
        return estudianteRepo.save(estudiante);
    }

    @Override
    public Estudiante actualizar(Estudiante estudiante) {
        return estudianteRepo.save(estudiante);
    }

    @Override
    public Optional buscarPorId(Long id) {
        return estudianteRepo.findById(id);
    }

    @Override
    public Estudiante buscarPorIdentificacion(String identificacion) {
        return estudianteRepo.findByIdentificacion(identificacion);
    }

    @Override
    public List<Estudiante> buscarEstudiantes() {
        return estudianteRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        estudianteRepo.deleteById(id);
    }
}
