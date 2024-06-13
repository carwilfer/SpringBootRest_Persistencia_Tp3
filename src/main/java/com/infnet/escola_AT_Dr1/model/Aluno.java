package com.infnet.escola_AT_Dr1.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "alunos")
@Data
public class Aluno {
    @Id
    private String id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;
}
