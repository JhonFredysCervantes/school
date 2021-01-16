package com.hardteach.school.entitiesTest;

import com.hardteach.school.entities.Estudiante;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


import java.util.Date;

public class EstudianteTest {

    private Estudiante e = new Estudiante();

    @Test
    public void testToSettersAndGetters(){
        Long id = 1L;
        String pNombre = "Jhon";
        String sNombre = "Fredys";
        String pApellido = "Cervantes";
        String sApellido = "Charris";
        char genero = 'M';
        Date nacimiento = new Date(2000,3,5);

        e.setId(id);
        e.setPrimerNombre(pNombre);
        e.setSegundoNombre(sNombre);
        e.setPrimerApellido(pApellido);
        e.setSegundoApellido(sApellido);
        e.setGenero(genero);
        e.setNacimiento(nacimiento);

        Assertions.assertEquals(e.getId(),id,"Fallo en seteo de id");
        Assertions.assertEquals(e.getPrimerNombre(),pNombre,"Fallo en seteo de primer Nombre");
        Assertions.assertEquals(e.getSegundoNombre(),sNombre, "Fallo en seteo de segundo Nombre");
        Assertions.assertEquals(e.getPrimerApellido(),pApellido, "Fallo en seteo de primer Apellido");
        Assertions.assertEquals(e.getSegundoApellido(),sApellido, "Fallo en seteo de segundo Apellido");
        Assertions.assertEquals(e.getGenero(),genero, "Fallo en seteo de genero");
        Assertions.assertEquals(e.getNacimiento(),nacimiento, "Fallo en seteo de fecha nacimiento");

    }
}
