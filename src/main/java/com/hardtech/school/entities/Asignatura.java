package com.hardtech.school.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hardtech.school.common.Constantes;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "asignaturas")
@Setter @Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Asignatura implements Serializable {

    @Id
    private String id;

    @NotEmpty
    @Column(nullable = false)
    private String nombre;

    @Min(Constantes.NUM_MIN_CREDITOS_ASIGNATURA)
    @Max(Constantes.NUM_MAX_CREDITOS_ASIGNATURA)
    @Column(nullable = false)
    private int nCreditos;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( name = "estudiante_asignatura",
            joinColumns = @JoinColumn(name="id_asignatura", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_estudiante", referencedColumnName = "id") )
    @JsonIgnoreProperties("asignaturas")
    private Set<Estudiante> estudiantes;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( name = "docente_asignatura",
            joinColumns = @JoinColumn(name="id_asignatura", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_docente", referencedColumnName = "id") )
    @JsonIgnoreProperties("asignaturas")
    private Set<Docente> docentes;

}
