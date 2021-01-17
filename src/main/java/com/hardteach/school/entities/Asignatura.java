package com.hardteach.school.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "asignaturas")
@Setter @Getter
@NoArgsConstructor
public class Asignatura {

    @Id
    private String id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int nCreditos;

    @ManyToMany
    @JoinTable( name = "estudiante_asignatura",
            joinColumns = @JoinColumn(name="id_asignatura", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_estudiante", referencedColumnName = "id") )
    @JsonIgnoreProperties("asignaturas")
    private Set<Estudiante> estudiantes;

    @ManyToMany
    @JoinTable( name = "docente_asignatura",
            joinColumns = @JoinColumn(name="id_asignatura", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_docente", referencedColumnName = "id") )
    @JsonIgnoreProperties("asignaturas")
    private Set<Docente> docentes;
}
