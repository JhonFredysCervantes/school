package com.hardteach.school.controllers.asignatura;

import com.hardteach.school.entities.Asignatura;
import com.hardteach.school.services.AsignaturaService;
import com.hardteach.school.common.Constantes;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.*;

@RestController
@RequestMapping(Constantes.PATH_REQUEST_COMMON_V1+"/asignaturas")
public class AsignaturaController {

    @Autowired
    AsignaturaService asigService;

    @ApiOperation( produces="application/json", value="Crear una asignatura", notes="Retorna La asignatura creada")
    @ApiResponse(code=HttpServletResponse.SC_OK, message="OK - created", response=Asignatura.class)
    @PostMapping
    /*public Asignatura crear(@ApiParam(value="Asignatura a guardar", name="Asignatura") @RequestBody Asignatura asig){
        return asigService.crearAsignatura(asig);
    }*/
    public AsignaturaResponse crear(@ApiParam(value="Asignatura a guardar", name="Crear") @RequestBody AsignaturaRequest asig){

        Asignatura asignatura = new Asignatura();
        AsignaturaResponse response = new AsignaturaResponse();

        asignatura.setId(1L); // Valor de prueba
        asignatura.setNombre(asig.getNombreAsignatura());
        asignatura.setDocente(asig.getDocenteAsignatura());
        asignatura.setNCreditos(asig.getNumeroCreditos());

        asignatura = asigService.crearAsignatura(asignatura);

        response.setNombreAsignatura(asignatura.getNombre());

        return response;
    }

    @ApiOperation(produces="application/json", value="Actualizar una asignatura", notes="Retorna La asignatura actualizada")
    @ApiResponse(code=HttpServletResponse.SC_OK, message="OK - updated", response=Asignatura.class)
    @PutMapping("/{ASIGNATURA_ID}")
    public AsignaturaRequest actualizar(@RequestBody AsignaturaRequest asig, @PathVariable("ASIGNATURA_ID") Long id){

        if(id!=null){
            var oldOptional = asigService.buscarAsignatura(id);
            if(oldOptional.isPresent()){
                Asignatura old = oldOptional.get();

                old.setNombre(asig.getNombreAsignatura());
                old.setDocente(asig.getDocenteAsignatura());
                old.setNCreditos(asig.getNumeroCreditos());

                old =  asigService.actualizarAsignatura(old);

                asig.setNombreAsignatura(old.getNombre());
                asig.setDocenteAsignatura(old.getDocente());
                asig.setNumeroCreditos(old.getNCreditos());

                return asig;
            }
        }
        return null;
    }

    /*@ApiOperation(produces="application/json", value="Actualizar una asignatura", notes="Retorna La asignatura actualizada")
    @ApiResponse(code=HttpServletResponse.SC_OK, message="OK - updated", response=Asignatura.class)
    @PatchMapping("/{ASIGNATURA_ID}")
    public Asignatura actualizarPatch(@RequestBody Map<String, Object> campos, @PathVariable("ASIGNATURA_ID") Long id){

        if(id!=null){
            var varOptional = asigService.buscarAsignatura(id);

            if(varOptional.isPresent()){

                Asignatura asig = varOptional.get();

                campos.forEach((c,v)->{
                    Field campo = ReflectionUtils.findField(Asignatura.class, c);
                    if(campo.getName()!="id"){
                        campo.setAccessible(true);
                        ReflectionUtils.setField(campo,asig,v);
                        campo.setAccessible(false);
                    }
                });
                return asigService.actualizarAsignatura(asig);
            }
        }
        return null;
    }*/

    @ApiOperation( produces="application/json", value="Buscar una asignatura", notes="Retorna La asignatura encontrada")
    @ApiResponses({
            @ApiResponse(code=HttpServletResponse.SC_OK, message="OK - created", response=Asignatura.class),
            @ApiResponse(code=HttpServletResponse.SC_BAD_REQUEST, message="Recurso no encontrado")
    })
    @GetMapping("/{ASIGNATURA_ID}")
    public AsignaturaResponse obtenerUna(@PathVariable("ASIGNATURA_ID")Long id){
        var asigOptional = asigService.buscarAsignatura(id);
        AsignaturaResponse asig = new AsignaturaResponse();
        if(asigOptional.isPresent()){
            asig.setNombreAsignatura(asigOptional.get().getNombre());
            return asig;
        }
        return null;
    }

    @ApiOperation(produces="application/json", value="Buscar todas las asignaturas", notes="Retorna todas la asignatura encontradas")
    @ApiResponses({
            @ApiResponse(code=HttpServletResponse.SC_OK, message="OK - found", response=List.class),
            @ApiResponse(code=HttpServletResponse.SC_BAD_REQUEST, message="No hay recursos que mostrar")
    })
    @GetMapping
    public List<AsignaturaResponse> obtenerTodas(){
        List<Asignatura> asignaturas = asigService.buscarAsignaturas();
        List<AsignaturaResponse> response = new ArrayList<AsignaturaResponse>();
        AsignaturaResponse asigResponse;

        for(Asignatura asig : asignaturas ){
            asigResponse = new AsignaturaResponse();
            asigResponse.setNombreAsignatura(asig.getNombre());
            response.add(asigResponse);
        }

        return response;
    }

    @ApiOperation(value = "Eliminar una asignatura", notes = "Elimina La asignatura referenciada" +
                                                              "con el id suministrado")
    @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK - Deleted")
    @DeleteMapping("/{ASIGNATURA_ID}")
    public void eliminar(@PathVariable("ASIGNATURA_ID") Long id){
        if(id != null){
            asigService.eliminarAsignatura(id);
        }
    }
}
