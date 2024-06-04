package com.infnet.web_rest_carlos_ferreira_tp3.services;

import com.infnet.web_rest_carlos_ferreira_tp3.models.Fornecedor;
import com.infnet.web_rest_carlos_ferreira_tp3.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<Fornecedor> listar() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> buscarPorId(Long id) {
        return fornecedorRepository.findById(id);
    }

    public Fornecedor criar(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public Optional<Fornecedor> atualizar(Long id, Fornecedor fornecedor) {
        return fornecedorRepository.findById(id)
                .map(existingFornecedor -> {
                    existingFornecedor.setNome(fornecedor.getNome());
                    existingFornecedor.setEmail(fornecedor.getEmail());
                    existingFornecedor.setEmpresa(fornecedor.getEmpresa());
                    return fornecedorRepository.save(existingFornecedor);
                });
    }

    public boolean deletar(Long id) {
        if (fornecedorRepository.existsById(id)) {
            fornecedorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
