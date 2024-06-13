package com.infnet.escola_AT_Dr1.controller;

import com.infnet.escola_AT_Dr1.dto.NotaDto;
import com.infnet.escola_AT_Dr1.model.Aluno;
import com.infnet.escola_AT_Dr1.model.Disciplina;
import com.infnet.escola_AT_Dr1.model.Nota;
import com.infnet.escola_AT_Dr1.service.AlunoService;
import com.infnet.escola_AT_Dr1.service.DisciplinaService;
import com.infnet.escola_AT_Dr1.service.NotaService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private DisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<Nota> atribuirNota(@RequestBody Nota nota) {
        Nota novaNota = notaService.atribuirNota(nota);
        return ResponseEntity.ok(novaNota);
    }

    @GetMapping("/disciplina/{disciplinaId}/aprovados")
    public ResponseEntity<List<NotaDto>> listarAprovados(@PathVariable String disciplinaId) {
        List<Nota> aprovados = notaService.listarAprovados(disciplinaId);
        List<NotaDto> aprovadosDto = aprovados.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(aprovadosDto);
    }

    @GetMapping("/disciplina/{disciplinaId}/reprovados")
    public ResponseEntity<List<NotaDto>> listarReprovados(@PathVariable String disciplinaId) {
        List<Nota> reprovados = notaService.listarReprovados(disciplinaId);
        List<NotaDto> reprovadosDto = reprovados.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reprovadosDto);
    }

    private NotaDto convertToDto(Nota nota) {
        Aluno aluno = alunoService.buscarAlunoPorId(nota.getAlunoId());
        Disciplina disciplina = disciplinaService.buscarDisciplinaPorId(nota.getDisciplinaId());
        return new NotaDto(nota, aluno.getNome(), disciplina.getNome());
    }
}


