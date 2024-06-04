package com.infnet.web_rest_carlos_ferreira_tp3.controller;

import com.infnet.web_rest_carlos_ferreira_tp3.models.Funcionario;
import com.infnet.web_rest_carlos_ferreira_tp3.services.FuncionarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<Funcionario> listar() {
        return funcionarioService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscar(@PathVariable Long id) {
        return funcionarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Funcionario> criar(@RequestBody Funcionario funcionario) {
        Funcionario savedFuncionario = funcionarioService.criar(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFuncionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        return funcionarioService.atualizar(id, funcionario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return funcionarioService.deletar(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
