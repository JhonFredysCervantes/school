package com.hardtech.school.controllers.asignatura.get;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class AsignaturaResponseGet implements Serializable {

    @ApiModelProperty(position = 0)
    private String idAsignatura;

    @ApiModelProperty(position = 1)
    private String nombreAsignatura;

    @ApiModelProperty(position = 2)
    private int numeroCreditos;
}
