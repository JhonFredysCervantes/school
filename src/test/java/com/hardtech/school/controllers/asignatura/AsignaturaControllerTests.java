package com.hardtech.school.controllers;

import com.hardtech.school.controllers.asignatura.AsignaturaController;
import com.hardtech.school.controllers.asignatura.create.AsignaturaRequestCreate;
import com.hardtech.school.controllers.asignatura.update.AsignaturaRequestUpdate;
import com.hardtech.school.controllers.exceptions.BadRequestException;
import com.hardtech.school.controllers.exceptions.NotFoundException;
import com.hardtech.school.entities.Asignatura;
import com.hardtech.school.objects_test.AsignaturaObjetosTest;
import com.hardtech.school.services.AsignaturaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class AsignaturaControllerTests {

    @Mock
    private AsignaturaService asignaturaService;

    private final AsignaturaController asignaturaController;

    public AsignaturaControllerTests(){
        openMocks(this);
        this.asignaturaController = new AsignaturaController(asignaturaService);

        Asignatura asignaturaValida = AsignaturaObjetosTest.asignaturaValida();

        //cuando se ejecuta guardar asignatura en el servicio
        when(asignaturaService.crearAsignatura(any(Asignatura.class))).thenAnswer(
                new Answer<Asignatura>() {
                    public Asignatura answer(InvocationOnMock invocation){
                        return invocation.getArgument(0);
                    }
                }
        );

        //cuando se ejecuta actualizar asignatura en el servicio
        when(asignaturaService.actualizarAsignatura(any(Asignatura.class))).thenAnswer(
                new Answer<Asignatura>() {
                    public Asignatura answer(InvocationOnMock invocation){
                        return invocation.getArgument(0);
                    }
                }
        );

        //cuando se ejecuta buscar asignatura por id en el servicio
        when(asignaturaService.buscarAsignatura(asignaturaValida.getId())).
                thenReturn(Optional.of(asignaturaValida));

    }

    @Test
    public void Crear_Asignatura_Exitosamente(){

        final int STATUS_CREATED = 201;

        Asignatura asignatura = AsignaturaObjetosTest.asignaturaValida();

        AsignaturaRequestCreate asignaturaRequestCreate = new AsignaturaRequestCreate(asignatura.getNombre(),asignatura.getNCreditos());

        when(asignaturaService.crearAsignatura(any(Asignatura.class))).thenReturn(asignatura);

        var result =  asignaturaController.saveSubject(asignaturaRequestCreate);

        assertNotNull(result.getBody(),"El resultado de la accion es nulo");
        assertThat(result.getBody().getIdAsignatura()).isEqualTo(asignatura.getId());
        assertThat(result.getBody().getNombreAsignatura()).isEqualTo(asignatura.getNombre());
        assertThat(result.getBody().getNumeroCreditos()).isEqualTo(asignatura.getNCreditos());
        assertThat(result.getStatusCodeValue()).isEqualTo(STATUS_CREATED);
    }

    @Test
    public void Crear_Asignatura_Invalida_Sin_Nombre(){

        Asignatura asignatura = AsignaturaObjetosTest.asignaturaSinNombre();

        AsignaturaRequestCreate asignaturaRequestCreate = new AsignaturaRequestCreate(asignatura.getNombre(),asignatura.getNCreditos());

        assertThrows(BadRequestException.class, ()->asignaturaController.saveSubject(asignaturaRequestCreate));

    }

    @Test
    public void Crear_Asignatura_Con_Numero_De_Creditos_Invalido_Negativo(){

        Asignatura asignatura = AsignaturaObjetosTest.asignaturaCreditoNegativo();

        AsignaturaRequestCreate asignaturaRequestCreate = new AsignaturaRequestCreate(asignatura.getNombre(),asignatura.getNCreditos());

        assertThrows(BadRequestException.class, ()->asignaturaController.saveSubject(asignaturaRequestCreate));

    }

    @Test
    public void Crear_Asignatura_Con_Numero_De_Creditos_Invalido_Mayor_Al_Tope(){

        Asignatura asignatura = AsignaturaObjetosTest.asignaturaCreditoMayorTopeSup();

        AsignaturaRequestCreate asignaturaRequestCreate = new AsignaturaRequestCreate(asignatura.getNombre(),asignatura.getNCreditos());

        assertThrows(BadRequestException.class, ()->asignaturaController.saveSubject(asignaturaRequestCreate));

    }

    @Test
    public void Buscar_Asignatura_Almacenada_Por_Id(){

        Asignatura asignatura = AsignaturaObjetosTest.asignaturaValida();

        final int OK = 200;

        var result = asignaturaController.findSubject(asignatura.getId());

        assertNotNull(result);

        assertThat(asignatura.getId()).isEqualTo(result.getBody().getIdAsignatura());
        assertThat(asignatura.getNombre()).isEqualTo(result.getBody().getNombreAsignatura());

        assertThat(result.getStatusCodeValue()).isEqualTo(OK);

    }

    @Test
    public void Buscar_Asignatura_Por_Id_No_Almacenada(){

        String invalidId = "none";

        when(asignaturaService.buscarAsignatura(invalidId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, ()->asignaturaController.findSubject(invalidId));

    }

    @Test
    public void Buscar_Asignatura_Por_Id_Nulo(){
        assertThrows(BadRequestException.class, ()->asignaturaController.findSubject(null));
    }

    @Test
    public void Actualizar_Asignatura_Eitosamente(){

        final int OK = 200;

        Asignatura asignatura = AsignaturaObjetosTest.asignaturaValida();
        AsignaturaRequestUpdate asignaturaRequest = new AsignaturaRequestUpdate(asignatura.getNombre(),asignatura.getNCreditos());

        var result = asignaturaController.updateSubject(asignaturaRequest,asignatura.getId());

        assertNotNull(result);
        assertThat(result.getStatusCodeValue()).isEqualTo(OK);
        assertThat(result.getBody().getNombreAsignatura()).isEqualTo(asignatura.getNombre());
        assertThat(result.getBody().getNumeroCreditos()).isEqualTo(asignatura.getNCreditos());
    }

}
