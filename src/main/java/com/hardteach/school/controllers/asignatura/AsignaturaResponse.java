package com.hardteach.school.controllers.asignatura;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class AsignaturaResponse {

    @ApiModelProperty(position = 0)
    private String nombreAsignatura;

}
