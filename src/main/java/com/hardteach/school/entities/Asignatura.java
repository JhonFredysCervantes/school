package com.hardteach.school.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "asignaturas")
@Setter @Getter
@NoArgsConstructor
public class Asignatura {

    @Id
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column
    private String docente;

    @Column(nullable = false)
    private int nCreditos;
}
