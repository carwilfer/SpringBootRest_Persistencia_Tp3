package com.infnet.web_rest_carlos_ferreira_tp3.services;

import com.infnet.web_rest_carlos_ferreira_tp3.models.Funcionario;
import com.infnet.web_rest_carlos_ferreira_tp3.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listar() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario criar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Optional<Funcionario> atualizar(Long id, Funcionario funcionario) {
        return funcionarioRepository.findById(id)
                .map(existingFuncionario -> {
                    existingFuncionario.setNome(funcionario.getNome());
                    existingFuncionario.setEmail(funcionario.getEmail());
                    existingFuncionario.setCargo(funcionario.getCargo());
                    existingFuncionario.setSalario(funcionario.getSalario());
                    return funcionarioRepository.save(existingFuncionario);
                });
    }

    public boolean deletar(Long id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
