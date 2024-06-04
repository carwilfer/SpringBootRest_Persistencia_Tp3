package com.infnet.web_rest_carlos_ferreira_tp3.controller;

import com.infnet.web_rest_carlos_ferreira_tp3.models.Fornecedor;
import com.infnet.web_rest_carlos_ferreira_tp3.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public List<Fornecedor> listar() {
        return fornecedorService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscar(@PathVariable Long id) {
        return fornecedorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Fornecedor> criar(@RequestBody Fornecedor fornecedor) {
        Fornecedor savedFornecedor = fornecedorService.criar(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFornecedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> atualizar(@PathVariable Long id, @RequestBody Fornecedor fornecedor) {
        return fornecedorService.atualizar(id, fornecedor)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return fornecedorService.deletar(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
