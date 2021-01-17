package com.hardteach.school.controllers.asignatura;

import com.hardteach.school.controllers.asignatura.create.AsignaturaRequestCreate;
import com.hardteach.school.controllers.asignatura.create.AsignaturaResponseCreate;
import com.hardteach.school.controllers.asignatura.get.AsignaturaResponseGet;
import com.hardteach.school.controllers.asignatura.get.AsignaturaResponseGetAll;
import com.hardteach.school.controllers.asignatura.update.AsignaturaRequestUpdate;
import com.hardteach.school.controllers.asignatura.update.AsignaturaResponseUpdate;
import com.hardteach.school.entities.Asignatura;
import com.hardteach.school.services.AsignaturaService;
import com.hardteach.school.common.Constantes;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping(Constantes.PATH_REQUEST_COMMON_V1+"/asignaturas")
public class AsignaturaController {

    @Autowired
    AsignaturaService asigService;

    @ApiOperation( produces="application/json", value="Crear una asignatura", notes="Retorna La asignatura creada")
    @ApiResponse(code=HttpServletResponse.SC_OK, message="OK - created", response= AsignaturaResponseCreate.class)
    @PostMapping
    public AsignaturaResponseCreate crear(@ApiParam(value="Asignatura a guardar", name="Crear") @RequestBody AsignaturaRequestCreate asig){

        Asignatura asignatura = new Asignatura();
        AsignaturaResponseCreate response = new AsignaturaResponseCreate();
        String id;

        do{
            id = (UUID.randomUUID()).toString().replace("-","").substring(0,4);
        }while(asigService.buscarAsignatura(id).isPresent());

        asignatura.setId(id);
        asignatura.setNombre(asig.getNombreAsignatura());
        asignatura.setNCreditos(asig.getNumeroCreditos());
        asignatura.setDocentes(null);
        asignatura.setEstudiantes(null);

        asignatura = asigService.crearAsignatura(asignatura);

        response.setNombreAsignatura(asignatura.getNombre());
        response.setNumeroCreditos(asignatura.getNCreditos());

        return response;
    }

    @ApiOperation(produces="application/json", value="Actualizar una asignatura", notes="Retorna La asignatura actualizada")
    @ApiResponse(code=HttpServletResponse.SC_OK, message="OK - updated", response= AsignaturaRequestCreate.class)
    @PutMapping("/{ASIGNATURA_ID}")
    public AsignaturaResponseUpdate actualizar(@RequestBody AsignaturaRequestUpdate asig, @PathVariable("ASIGNATURA_ID") String id){

        if(id!=null){
            Optional<Asignatura> oldOptional = asigService.buscarAsignatura(id);
            if(oldOptional.isPresent() && (asig.getIdAsignatura()==id)){
                Asignatura old = oldOptional.get();

                old.setNombre(asig.getNombreAsignatura());
                old.setNCreditos(asig.getNumeroCreditos());

                old =  asigService.actualizarAsignatura(old);

                AsignaturaResponseUpdate response = new AsignaturaResponseUpdate();
                response.setIdAsignatura(id);
                response.setNombreAsignatura(old.getNombre());
                response.setNumeroCreditos(old.getNCreditos());

                return response;
            }
        }
        return null;
    }

    @ApiOperation( produces="application/json", value="Buscar una asignatura", notes="Retorna La asignatura encontrada")
    @ApiResponses({
            @ApiResponse(code=HttpServletResponse.SC_OK, message="OK - created", response= AsignaturaResponseCreate.class),
            @ApiResponse(code=HttpServletResponse.SC_BAD_REQUEST, message="Recurso no encontrado")
    })
    @GetMapping("/{ASIGNATURA_ID}")
    public AsignaturaResponseGet obtenerUna(@PathVariable("ASIGNATURA_ID")String id){

        var asigOptional = asigService.buscarAsignatura(id);
        Asignatura asig;
        AsignaturaResponseGet response = new AsignaturaResponseGet();

        if(asigOptional.isPresent()){
            asig = asigOptional.get();
            response.setIdAsignatura(id);
            response.setNombreAsignatura(asig.getNombre());
            response.setNumeroCreditos(asig.getNCreditos());

            return response;
        }
        return null;
    }

    @ApiOperation(produces="application/json", value="Buscar todas las asignaturas", notes="Retorna todas la asignatura encontradas")
    @ApiResponses({
            @ApiResponse(code=HttpServletResponse.SC_OK, message="OK - found", response=List.class),
            @ApiResponse(code=HttpServletResponse.SC_BAD_REQUEST, message="No hay recursos que mostrar")
    })
    @GetMapping
    public List<AsignaturaResponseGetAll> obtenerTodas(){
        List<Asignatura> asignaturas = asigService.buscarAsignaturas();
        List<AsignaturaResponseGetAll> response = new ArrayList<AsignaturaResponseGetAll>();
        AsignaturaResponseGetAll asigResponse;

        for(Asignatura asig : asignaturas ){
            asigResponse = new AsignaturaResponseGetAll();
            asigResponse.setIdAsignatura(asig.getId());
            asigResponse.setNombreAsignatura(asig.getNombre());
            asigResponse.setNumeroCreditos(asig.getNCreditos());
            response.add(asigResponse);
        }

        return response;
    }

    @ApiOperation(value = "Eliminar una asignatura", notes = "Elimina La asignatura referenciada" +
                                                              "con el id suministrado")
    @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK - Deleted")
    @DeleteMapping("/{ASIGNATURA_ID}")
    public void eliminar(@PathVariable("ASIGNATURA_ID") String id){
        if(id != null){
            asigService.eliminarAsignatura(id);
        }
    }
}
