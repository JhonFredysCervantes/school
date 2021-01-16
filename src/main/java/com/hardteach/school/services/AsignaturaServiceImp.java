package com.hardteach.school.services;

import com.hardteach.school.entities.Asignatura;
import com.hardteach.school.repositories.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaServiceImp implements AsignaturaService{

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
    public Optional<Asignatura> buscarAsignatura(Long id) {
        return asigRepo.findById(id);
    }

    @Override
    public List<Asignatura> buscarAsignaturas(){
        return asigRepo.findAll();
    }

    @Override
    public void eliminarAsignatura(Long id) {
        asigRepo.deleteById(id);
    }
}
