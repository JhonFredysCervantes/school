package com.hardteach.school.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="docentes")
@Setter @Getter
@NoArgsConstructor
public class Docente {

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

    @Column(nullable = false)
    private String telefono;

    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date nacimiento;

    @ManyToMany(mappedBy = "docentes",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnoreProperties("docentes")
    private Set<Asignatura> asignaturas;
}
