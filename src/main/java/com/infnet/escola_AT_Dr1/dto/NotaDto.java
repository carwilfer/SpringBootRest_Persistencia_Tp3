package com.infnet.escola_AT_Dr1.dto;

import com.infnet.escola_AT_Dr1.model.Nota;
import lombok.Data;

@Data
public class NotaDto {
    private String id;
    private String alunoId;
    private String alunoNome;
    private String disciplinaId;
    private String disciplinaNome;
    private double valor;

    public NotaDto(Nota nota, String alunoNome, String disciplinaNome) {
        this.id = nota.getId();
        this.alunoId = nota.getAlunoId();
        this.alunoNome = alunoNome;
        this.disciplinaId = nota.getDisciplinaId();
        this.disciplinaNome = disciplinaNome;
        this.valor = nota.getValor();
    }
}
