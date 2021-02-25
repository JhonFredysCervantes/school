package com.hardtech.school.services;

import com.hardtech.school.common.Constantes;
import com.hardtech.school.entities.Asignatura;
import com.hardtech.school.objects_test.AsignaturaObjetosTest;
import com.hardtech.school.repositories.AsignaturaRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.BDDMockito;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;


import java.util.Optional;


@SpringBootTest
@TestPropertySource(locations = "classpath:aplicationTest.properties")
public class IAsignaturaServiceTest {

    @MockBean
    AsignaturaRepository asignaturaRepository;

    @Autowired
    IAsignaturaService asignaturaService;

    @BeforeEach
    public void parametrizandoMock(){
        //Buscar en repo
        BDDMockito.given(this.asignaturaRepository.findById("12345"))
                .willReturn(Optional.of(AsignaturaObjetosTest.asignaturaValida()));
        //guardar en repo
        BDDMockito.when(this.asignaturaRepository.save(Mockito.any(Asignatura.class))).
                thenAnswer(
                        new Answer<Asignatura>() {
                            public Asignatura answer(InvocationOnMock invocation) throws Exception {

                                Asignatura asignatura = invocation.getArgument(0);
                                String nombre = asignatura.getNombre();
                                int creditos = asignatura.getNCreditos();

                                if(nombre.isEmpty() || (nombre ==null) ||
                                        (creditos < Constantes.NUM_MIN_CREDITOS_ASIGNATURA) ||
                                                (creditos > Constantes.NUM_MAX_CREDITOS_ASIGNATURA)){
                                    throw new Exception("Asignatura invalida");
                                }

                                return asignatura;
                            }
                        }
                );
    }

    @Test
    public void obtenerAsignaturaTest(){
        Assertions.assertEquals(this.asignaturaService.buscarAsignatura("12345"),
                Optional.of(AsignaturaObjetosTest.asignaturaValida()));
    }

    @Test
    public void obtenerAsignaturaInvalidaTest(){
        Assertions.assertNotEquals(this.asignaturaService.buscarAsignatura("12345"),
                Optional.of(AsignaturaObjetosTest.asignaturaSinId()));
    }

    @Test
    public void salvarAsignaturaTest(){
        Assertions.assertEquals(this.asignaturaService.crearAsignatura(AsignaturaObjetosTest.asignaturaValida()),
                AsignaturaObjetosTest.asignaturaValida());
    }

    @Test
    public void salvarAsignaturaInvalidaTest(){

        Assertions.assertThrows(Exception.class,()->{
                this.asignaturaService.crearAsignatura(AsignaturaObjetosTest.asignaturaCreditoNegativo());});
    }



}
