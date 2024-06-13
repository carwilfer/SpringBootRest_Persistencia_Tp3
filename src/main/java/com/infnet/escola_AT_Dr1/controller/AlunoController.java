package com.infnet.escola_AT_Dr1.controller;

import com.infnet.escola_AT_Dr1.model.Aluno;
import com.infnet.escola_AT_Dr1.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno) {
        Aluno novoAluno = alunoService.cadastrarAluno(aluno);
        return ResponseEntity.ok(novoAluno);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        List<Aluno> alunos = alunoService.listarAlunos();
        return ResponseEntity.ok(alunos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable String id, @RequestBody Aluno aluno) {
        aluno.setId(id);
        Aluno alunoAtualizado = alunoService.atualizarAluno(aluno);
        return ResponseEntity.ok(alunoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable String id) {
        alunoService.deletarAluno(id);
        return ResponseEntity.noContent().build();
    }
}
