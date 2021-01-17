package com.hardteach.school.controllers.asignatura.get;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class AsignaturaResponseGet {

    @ApiModelProperty(position = 0)
    private String idAsignatura;

    @ApiModelProperty(position = 1)
    private String nombreAsignatura;

    @ApiModelProperty(position = 2)
    private int numeroCreditos;
}
