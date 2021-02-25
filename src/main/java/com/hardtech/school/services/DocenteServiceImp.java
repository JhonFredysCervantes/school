package com.hardtech.school.services;

import com.hardtech.school.entities.Docente;
import com.hardtech.school.repositories.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DocenteServiceImp implements IDocenteService {

    @Autowired
    private DocenteRepository docenteRepo;

    @Override
    public Docente crear(Docente docente) {
        return docenteRepo.save(docente);
    }

    @Override
    public Docente actualizar(Docente docente) {
        return docenteRepo.save(docente);
    }

    @Override
    public Optional buscarPorId(Long id) {
        return docenteRepo.findById(id);
    }

    @Override
    public Docente buscarPorIdentificacion(String identificacion) {
        return docenteRepo.findByIdentificacion(identificacion);
    }

    @Override
    public List<Docente> buscarDocentes() {
        return docenteRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        docenteRepo.deleteById(id);
    }

}
