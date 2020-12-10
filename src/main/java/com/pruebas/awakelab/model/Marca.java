package com.pruebas.awakelab.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Marca {

    @Id
    @SequenceGenerator(name = "secMar", sequenceName="SECMAR", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secMar")
    private Integer id;

    @Column(nullable = false)
    @NotEmpty
    private String nombre;

}
