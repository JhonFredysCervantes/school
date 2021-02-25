package com.hardtech.school.controllers.asignatura.create;


import com.hardtech.school.common.Constantes;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class AsignaturaRequestCreate {

    @ApiModelProperty(position = 0, required = true,example = "Materia")
    private String nombreAsignatura;

    @ApiModelProperty(position = 1, required = true,example = "4")
    private int numeroCreditos;

    public String validate(){
        String message = "";
        final int MIN_CREDITO = Constantes.NUM_MIN_CREDITOS_ASIGNATURA;
        final int MAX_CREDITO = Constantes.NUM_MAX_CREDITOS_ASIGNATURA;

        if(this.nombreAsignatura == null){
            message = message.concat(" El nombre de la Asignatura no puede ser nulo. ");
        }else if(this.nombreAsignatura.isEmpty()){
            message = message.concat(" El nombre de la Asignatura no puede estar en blanco. ");
        }
        if(this.numeroCreditos< MIN_CREDITO || this.numeroCreditos > MAX_CREDITO){
            message = message.concat(" El numero de creditos tiene que estar entre: "+MIN_CREDITO+" Y "+MAX_CREDITO);
        }
        return message;
    }

}
