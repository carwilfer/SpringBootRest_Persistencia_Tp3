package com.infnet.web_rest_carlos_ferreira_tp3.models;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Fornecedor extends Pessoa{

    private String empresa;

}
