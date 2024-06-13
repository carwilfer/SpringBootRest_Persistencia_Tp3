package com.infnet.escola_AT_Dr1.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notas")
@Data
public class Nota {
    @Id
    private String id;
    private String alunoId;
    private String disciplinaId;
    private double valor;
}
