package com.hardteach.school.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "asignaturas")
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Asignatura implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asignatura that = (Asignatura) o;
        return nCreditos == that.nCreditos && Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(estudiantes, that.estudiantes) && Objects.equals(docentes, that.docentes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
