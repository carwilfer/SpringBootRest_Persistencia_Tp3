package com.infnet.web_rest_carlos_ferreira_tp3.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@Getter
@Setter
public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;
}
