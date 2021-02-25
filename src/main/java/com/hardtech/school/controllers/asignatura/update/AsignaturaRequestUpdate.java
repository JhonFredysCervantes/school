package com.hardtech.school.controllers.asignatura.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class AsignaturaRequestUpdate {

    @ApiModelProperty(position = 0, required = true, example = "Matematicas")
    private String nombreAsignatura;

    @ApiModelProperty(position = 1, required = true, example = "4")
    private int numeroCreditos;

    public String validate(){
        String message = "";
        final int MIN_CREDITO = 1;
        final int MAX_CREDITO = 8;

        if(this.nombreAsignatura == null){
            message = message.concat(" El nombre de la Asignatura no debe ser nulo. ");
        }else if(this.nombreAsignatura.isEmpty()){
            message = message.concat(" El nombre de la Asignatura no debe estar vacio. ");
        }
        if(this.numeroCreditos< MIN_CREDITO || this.numeroCreditos > MAX_CREDITO){
            message = message.concat(" El numero de creditos tiene que estar entre: "+MIN_CREDITO+" Y "+MAX_CREDITO);
        }
        return message;
    }


}
