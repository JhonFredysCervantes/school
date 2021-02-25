package com.hardteach.school.controllers;

import com.hardteach.school.common.Constantes;
import com.hardteach.school.controllers.asignatura.AsignaturaController;
import com.hardteach.school.controllers.asignatura.create.AsignaturaRequestCreate;
import com.hardteach.school.controllers.asignatura.create.AsignaturaResponseCreate;
import com.hardteach.school.controllers.asignatura.get.AsignaturaResponseGet;
import com.hardteach.school.entities.Asignatura;
import com.hardteach.school.objects_test.AsignaturaObjetosTest;
import com.hardteach.school.services.AsignaturaService;
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
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
public class AsignaturaControllerTest {

    @MockBean
    private AsignaturaService asignaturaService;

    @Autowired
    private AsignaturaController asignaturaController;

    @BeforeEach
    public void inicializar(){
        BDDMockito.when(asignaturaService.crearAsignatura(Mockito.any(Asignatura.class))).
                thenAnswer(new Answer<Asignatura>() {
                               public Asignatura answer(InvocationOnMock invocation){
                                   return invocation.getArgument(0);
                               }
                });
        BDDMockito.when(asignaturaService.buscarAsignatura("12345")).
                thenReturn( Optional.of(AsignaturaObjetosTest.asignaturaValida() ));
    }

    @Test
    public void guardarAsignaturaValidaControllerTest(){

        Asignatura asignatura = AsignaturaObjetosTest.asignaturaSinId();

        AsignaturaRequestCreate request = new AsignaturaRequestCreate(asignatura.getNombre(), asignatura.getNCreditos());

        ResponseEntity<AsignaturaResponseCreate> response = asignaturaController.saveSubject(request);

        Assertions.assertTrue( (response.getStatusCodeValue() == 201)
                &&(request.getNombreAsignatura() == response.getBody().getNombreAsignatura()));

    }

    @Test
    public void guardarAsignaturaNombreInvalidoControllerTest(){

        AsignaturaRequestCreate request = new AsignaturaRequestCreate(null, 4);

        Assertions.assertThrows(RuntimeException.class,()->{asignaturaController.saveSubject(request);});

    }

    @Test
    public void guardarAsignaturaCreditoInvalidoControllerTest(){

        AsignaturaRequestCreate requestCredMenorTope = new AsignaturaRequestCreate("Materia", -1);
        AsignaturaRequestCreate requestCredMayorTope = new AsignaturaRequestCreate("Materia", Constantes.NUM_MAX_CREDITOS_ASIGNATURA+1);

        Assertions.assertThrows(RuntimeException.class,()->{asignaturaController.saveSubject(requestCredMenorTope);});
        Assertions.assertThrows(RuntimeException.class,()->{asignaturaController.saveSubject(requestCredMayorTope);});

    }

    @Test
    public void obtenerAsignaturaControllerTest(){

        final int OK = 200;

        Asignatura asignatura = AsignaturaObjetosTest.asignaturaValida();

        AsignaturaRequestCreate request = new AsignaturaRequestCreate(asignatura.getNombre(), asignatura.getNCreditos());

        ResponseEntity<AsignaturaResponseGet> response = asignaturaController.findSubject(asignatura.getId());

        Assertions.assertTrue(response.getStatusCodeValue()==OK);

        Assertions.assertTrue(asignatura.getId() == response.getBody().getIdAsignatura());

    }

}
