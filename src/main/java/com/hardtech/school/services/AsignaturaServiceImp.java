package com.hardtech.school.services;

import com.hardtech.school.entities.Asignatura;
import com.hardtech.school.repositories.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaServiceImp implements IAsignaturaService {

    @Autowired
    AsignaturaRepository asigRepo;

    @Override
    public Asignatura crearAsignatura(Asignatura asig) {
        return asigRepo.save(asig);
    }

    @Override
    public Asignatura actualizarAsignatura(Asignatura asig) {
        return asigRepo.save(asig);
    }

    @Override
    public Optional<Asignatura> buscarAsignatura(String id) {
        return asigRepo.findById(id);
    }

    @Override
    public List<Asignatura> buscarAsignaturas(){
        return asigRepo.findAll();
    }

    @Override
    public void eliminarAsignatura(String id) {
        asigRepo.deleteById(id);
    }
}
