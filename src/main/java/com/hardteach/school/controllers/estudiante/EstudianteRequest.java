package com.hardteach.school.controllers.estudiante;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Setter @Getter
public class EstudianteRequest {
    @ApiModelProperty(position = 0, required = true, example = "12345678")
    private Long numeroIdentificacion;

    @ApiModelProperty(position = 1, required = true, example = "Antonio")
    private String primerNombre;

    @ApiModelProperty(position = 2, example = "Rafael")
    private String segundoNombre;

    @ApiModelProperty(position = 3, required = true, example = "Lopez")
    private String primerApellido;

    @ApiModelProperty(position = 4, example = "Cardenas")
    private String segundoApellido;

    @ApiModelProperty(position = 5, example = "M",notes = "M : Masculino, F : Femenino")
    private char genero;

    @ApiModelProperty(position = 6, example = "example@email.com")
    private String email;

    @ApiModelProperty(position = 7, example = "12345678")
    private Long numContacto;

    @ApiModelProperty(position = 8, example = "1990-12-22")
    private Date fechaNacimiento;
}
