package com.infnet.web_rest_carlos_ferreira_tp3.models;

import javax.persistence.Entity;
import lombok.Getter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Funcionario extends Pessoa{

    private String cargo;
    private BigDecimal salario;
}
