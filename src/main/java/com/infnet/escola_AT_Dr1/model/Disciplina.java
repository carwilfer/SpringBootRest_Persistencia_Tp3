package com.infnet.escola_AT_Dr1.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "disciplinas")
@Data
public class Disciplina {
    @Id
    private String id;
    private String nome;
    private String codigo;
}
