package com.hardteach.school.controllers.asignatura;

import com.hardteach.school.controllers.asignatura.create.AsignaturaRequestCreate;
import com.hardteach.school.controllers.asignatura.create.AsignaturaResponseCreate;
import com.hardteach.school.controllers.asignatura.get.AsignaturaResponseGet;
import com.hardteach.school.controllers.asignatura.get.AsignaturaResponseGetAll;
import com.hardteach.school.controllers.asignatura.update.AsignaturaRequestUpdate;
import com.hardteach.school.controllers.asignatura.update.AsignaturaResponseUpdate;
import com.hardteach.school.controllers.exceptions.BadRequestException;
import com.hardteach.school.controllers.exceptions.ConfictException;
import com.hardteach.school.controllers.exceptions.NotFoundException;
import com.hardteach.school.entities.Asignatura;
import com.hardteach.school.services.AsignaturaService;
import com.hardteach.school.common.Constantes;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping(Constantes.PATH_REQUEST_COMMON_V1+"/subject")
public class AsignaturaController {



    private final AsignaturaService asigService;

    @Autowired
    public AsignaturaController(AsignaturaService asigService) {
        this.asigService = asigService;
    }


    @ApiOperation( produces="application/json", value="Save subject", notes="Reuturns the saved object")
    @ApiResponses({
            @ApiResponse(code=HttpServletResponse.SC_CREATED, message="OK - saved",
                         response= AsignaturaResponseCreate.class),
            @ApiResponse(code=HttpServletResponse.SC_BAD_REQUEST, message="Error - wrong field")
    })
    @PostMapping
    public ResponseEntity<AsignaturaResponseCreate> saveSubject(@ApiParam(value="Subject to save",
                            name="Subject",required = true) @RequestBody AsignaturaRequestCreate request){

        Asignatura asignatura;
        AsignaturaResponseCreate response;
        String id;
        String validationResult = request.validate();

        if(!validationResult.isEmpty()){
            throw new BadRequestException(validationResult);
        }

        id = (UUID.randomUUID()).toString();

        asignatura = new Asignatura(id,request.getNombreAsignatura(),request.getNumeroCreditos(),null,null);
        asignatura = asigService.crearAsignatura(asignatura);
        response = new AsignaturaResponseCreate(asignatura.getId(),asignatura.getNombre(),asignatura.getNCreditos());

        return new ResponseEntity<>(response, HttpStatus.CREATED) ;
    }


    @ApiOperation(produces="application/json", value="Update sobject", notes="returns the updated subject")
    @ApiResponse(code=HttpServletResponse.SC_OK, message="OK - updated", response= AsignaturaRequestCreate.class)
    @PutMapping("/{SUBJECT_ID}")
    public ResponseEntity<AsignaturaResponseUpdate> updateSubject(@RequestBody AsignaturaRequestUpdate request, @PathVariable("SUBJECT_ID") String id){

        AsignaturaResponseUpdate response;
        Optional<Asignatura> oldOptional;
        String validationResult;

        if(id!=null){

            validationResult = request.validate();

            if(!validationResult.isEmpty()){
                throw new BadRequestException(validationResult);
            }

            oldOptional = asigService.buscarAsignatura(id);

            if(oldOptional.isPresent()){
                Asignatura old = oldOptional.get();

                old.setNombre(request.getNombreAsignatura());
                old.setNCreditos(request.getNumeroCreditos());

                old =  asigService.actualizarAsignatura(old);

                response = new AsignaturaResponseUpdate(old.getNombre(),old.getNCreditos());

                return new ResponseEntity<>(response,HttpStatus.OK);
            }else{
                throw new NotFoundException("Subject whit id:"+id+" not found");
            }
        }
        throw new BadRequestException("id (in the path) can't be null");
    }



    @ApiOperation( produces="application/json", value="Find subject", notes="return the found subject")
    @ApiResponses({
            @ApiResponse(code=HttpServletResponse.SC_OK, message="OK - found", response= AsignaturaResponseCreate.class),
            @ApiResponse(code=HttpServletResponse.SC_BAD_REQUEST, message="Not found")
    })
    @GetMapping("/{SUBJECT_ID}")
    public ResponseEntity<AsignaturaResponseGet> findSubject(@PathVariable("SUBJECT_ID")String id){

        Optional<Asignatura> subjectOptional;
        Asignatura subject;
        AsignaturaResponseGet response;

        if(id!=null){
            subjectOptional = asigService.buscarAsignatura(id);
            if(subjectOptional.isPresent()){
                subject = subjectOptional.get();
                response = new AsignaturaResponseGet(subject.getId(),subject.getNombre(), subject.getNCreditos());
                return new ResponseEntity<>(response,HttpStatus.OK);
            }else{
                throw new NotFoundException("Subject whit id:"+id+" not found");
            }
        }

        throw new BadRequestException("id (in the path) can't be null");
    }



    @ApiOperation(produces="application/json", value="find all subjects", notes="Return found subjects")
    @ApiResponses({
            @ApiResponse(code=HttpServletResponse.SC_OK, message="OK", response=List.class),
            @ApiResponse(code=HttpServletResponse.SC_NOT_FOUND, message="there aren't subjects")
    })
    @GetMapping
    public List<AsignaturaResponseGetAll> getAllSubjects(){
        List<Asignatura> subjects = asigService.buscarAsignaturas();
        List<AsignaturaResponseGetAll> response = new ArrayList<AsignaturaResponseGetAll>();
        AsignaturaResponseGetAll subjectsResponse;

        for(Asignatura subject : subjects ){
            subjectsResponse = new AsignaturaResponseGetAll(subject.getId(), subject.getNombre(),subject.getNCreditos());
            response.add(subjectsResponse);
        }

        return response;
    }



    @ApiOperation(value = "Deled subject", notes = "delete a sobject by id")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK - Deleted"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Bad Request - id null")
    })
    @DeleteMapping("/{SUBJECT_ID}")
    public ResponseEntity<?> deleteSubject(@PathVariable("SUBJECT_ID") String id){
        if(id != null){
            if(asigService.buscarAsignatura(id).isPresent()){
                asigService.eliminarAsignatura(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                throw new ConfictException("you can't eliminate subject, sobject does't exist");
            }
        }
        throw new BadRequestException("id (in the path) can't be null");
    }
}
