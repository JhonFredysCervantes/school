package com.hardteach.school.controllers.asignatura.create;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class AsignaturaResponseCreate {

    @ApiModelProperty(position = 0)
    private String nombreAsignatura;

    @ApiModelProperty(position = 1)
    private int numeroCreditos;

}
