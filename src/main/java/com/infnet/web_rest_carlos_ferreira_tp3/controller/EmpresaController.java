package com.infnet.web_rest_carlos_ferreira_tp3.controller;

import com.infnet.web_rest_carlos_ferreira_tp3.models.Empresa;
import com.infnet.web_rest_carlos_ferreira_tp3.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public List<Empresa> listar() {
        return empresaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscar(@PathVariable Long id) {
        return empresaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Empresa> criar(@RequestBody Empresa empresa) {
        Empresa savedEmpresa = empresaService.criar(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmpresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizar(@PathVariable Long id, @RequestBody Empresa empresa) {
        return empresaService.atualizar(id, empresa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return empresaService.deletar(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
