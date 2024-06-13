package com.infnet.escola_AT_Dr1.controller;

import com.infnet.escola_AT_Dr1.model.Disciplina;
import com.infnet.escola_AT_Dr1.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<Disciplina> cadastrarDisciplina(@RequestBody Disciplina disciplina) {
        Disciplina novaDisciplina = disciplinaService.cadastrarDisciplina(disciplina);
        return ResponseEntity.ok(novaDisciplina);
    }

    @GetMapping
    public ResponseEntity<List<Disciplina>> listarDisciplinas() {
        List<Disciplina> disciplinas = disciplinaService.listarDisciplinas();
        return ResponseEntity.ok(disciplinas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> atualizarDisciplina(@PathVariable String id, @RequestBody Disciplina disciplina) {
        disciplina.setId(id);
        Disciplina disciplinaAtualizada = disciplinaService.atualizarDisciplina(disciplina);
        return ResponseEntity.ok(disciplinaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDisciplina(@PathVariable String id) {
        disciplinaService.deletarDisciplina(id);
        return ResponseEntity.noContent().build();
    }
}
