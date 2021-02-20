package com.hardteach.school.controllers.asignatura.create;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class AsignaturaResponseCreate implements Serializable {

    @ApiModelProperty(position = 0)
    private String nombreAsignatura;

    @ApiModelProperty(position = 1)
    private int numeroCreditos;

}
