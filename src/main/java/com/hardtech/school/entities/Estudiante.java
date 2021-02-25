package com.hardtech.school.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "estudiantes")
@Setter @Getter
@NoArgsConstructor
public class Estudiante {
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
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

    @ManyToMany(mappedBy = "estudiantes",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnoreProperties("asignaturas")
    private Set<Asignatura> asignaturas;

}
