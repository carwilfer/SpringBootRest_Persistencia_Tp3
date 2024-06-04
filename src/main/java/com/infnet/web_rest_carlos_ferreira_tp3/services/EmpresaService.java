package com.infnet.web_rest_carlos_ferreira_tp3.services;

import com.infnet.web_rest_carlos_ferreira_tp3.models.Empresa;
import com.infnet.web_rest_carlos_ferreira_tp3.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> listar() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> buscarPorId(Long id) {
        return empresaRepository.findById(id);
    }

    public Empresa criar(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public Optional<Empresa> atualizar(Long id, Empresa empresa) {
        return empresaRepository.findById(id)
                .map(existingEmpresa -> {
                    existingEmpresa.setNome(empresa.getNome());
                    existingEmpresa.setCnpj(empresa.getCnpj());
                    existingEmpresa.setEndereco(empresa.getEndereco());
                    return empresaRepository.save(existingEmpresa);
                });
    }

    public boolean deletar(Long id) {
        if (empresaRepository.existsById(id)) {
            empresaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
