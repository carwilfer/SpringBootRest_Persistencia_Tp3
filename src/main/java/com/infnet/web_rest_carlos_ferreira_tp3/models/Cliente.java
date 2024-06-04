package com.infnet.web_rest_carlos_ferreira_tp3.models;

import javax.persistence.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Cliente extends Pessoa {
    private String endereco;

}
