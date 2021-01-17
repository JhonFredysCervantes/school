package com.hardteach.school.controllers.asignatura.create;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class AsignaturaRequestCreate {

    @ApiModelProperty(position = 0, required = true,example = "Materia")
    private String nombreAsignatura;

    @ApiModelProperty(position = 1, required = true,example = "4")
    private int numeroCreditos;

}
