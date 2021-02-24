package com.hardteach.school.repository_test;

import com.hardteach.school.objects_for_test.AsignaturaObjetosTest;
import com.hardteach.school.repositories.AsignaturaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
public class AsignaturaRepositoryIT {

    @Autowired
    private AsignaturaRepository asignaturaRepository;


    @BeforeEach
    public void initialization(){

    }

    @Test
    public void guardarValidaAsignaturaTest(){

        Assertions.assertEquals(AsignaturaObjetosTest.asignaturaValida(),
                asignaturaRepository.save(AsignaturaObjetosTest.asignaturaValida()));

    }

    @Test
    public void guardarAsignaturaNombreInvalidoTest(){

        Assertions.assertThrows(Exception.class,
                ()->{asignaturaRepository.save(AsignaturaObjetosTest.asignaturaSinNombre());});

    }

    @Test
    @Disabled
    public void guardarAsignaturaCreditoNegativoInvalidoTest(){

        Assertions.assertThrows(Exception.class,
                ()->{asignaturaRepository.save(AsignaturaObjetosTest.asignaturaCreditoNegativo());});

    }

    @Test
    @Disabled
    public void guardarAsignaturaCreditoExcedidoInvalidoTest(){

        Assertions.assertThrows(Exception.class,
                ()->{asignaturaRepository.save(AsignaturaObjetosTest.asignaturaCreditoMayorTopeSup());});

    }

    @Test
    @Disabled
    public void obtenerAsignaturaPorIdTest(){

        asignaturaRepository.save(AsignaturaObjetosTest.asignaturaValida());

        Assertions.assertEquals(Optional.of(AsignaturaObjetosTest.asignaturaValida()),
                asignaturaRepository.findById(AsignaturaObjetosTest.asignaturaValida().getId()));

    }

    @Test
    @Disabled
    public void obtenerAsignaturaPorNombreTest(){

        asignaturaRepository.save(AsignaturaObjetosTest.asignaturaValida());

        Assertions.assertEquals(AsignaturaObjetosTest.asignaturaValida(),
                asignaturaRepository.findByNombre(AsignaturaObjetosTest.asignaturaValida().getNombre()));

    }

}
