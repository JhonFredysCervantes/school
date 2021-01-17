package com.hardteach.school.controllers.asignatura.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class AsignaturaResponseUpdate {

    @ApiModelProperty(position = 0)
    private String idAsignatura;

    @ApiModelProperty(position = 1)
    private String nombreAsignatura;

    @ApiModelProperty(position = 2)
    private int numeroCreditos;

}
