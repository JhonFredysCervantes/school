package com.hardteach.school.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="docentes")
@Setter @Getter
@NoArgsConstructor
public class Docente {

    @Id
    private Long id;

    @Column
    private String identificacion;

    @Column(nullable = false)
    private String primerNombre;

    @Column
    private String segundoNombre;

    @Column(nullable = false)
    private String primerApellido;

    @Column
    private String segundoApellido;

    @Column
    private char genero;

    @Column
    private String email;

    @Column
    private String telefono;

    @Column
    private Date nacimiento;
}
