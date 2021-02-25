package com.hardtech.school.controllers.estudiante;

import com.hardtech.school.services.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;

public class EstudianteController {

    @Autowired
    private IEstudianteService estudianteService;
}
