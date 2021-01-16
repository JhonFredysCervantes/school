package com.hardteach.school.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cursos")
@Setter @Getter
@NoArgsConstructor
public class Curso {
    @Id
    private Long id;

    @Column
    private String gradoCurso;

}
