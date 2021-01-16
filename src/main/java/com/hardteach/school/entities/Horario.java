package com.hardteach.school.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Horario")
@Setter @Getter
@NoArgsConstructor
public class Horario {
    @Id
    private Long id;
}
