package com.hardteach.school.controllers.asignatura;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class AsignaturaRequest {
    @ApiModelProperty(position = 0, required = true,example = "Materia")
    private String nombreAsignatura;

    @ApiModelProperty(position = 1, required = true,example = "4")
    private int numeroCreditos;

    @ApiModelProperty(position = 2, example = "Docente")
    private String docenteAsignatura;

}
