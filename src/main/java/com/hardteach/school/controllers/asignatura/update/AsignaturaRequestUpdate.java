package com.hardteach.school.controllers.asignatura.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class AsignaturaRequestUpdate {

    @ApiModelProperty(position = 0, required = true, example = "1")
    private String idAsignatura;

    @ApiModelProperty(position = 1, required = true, example = "Matematicas")
    private String nombreAsignatura;

    @ApiModelProperty(position = 2, required = true, example = "4")
    private int numeroCreditos;




}
