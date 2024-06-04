package com.infnet.web_rest_carlos_ferreira_tp3.services;

import com.infnet.web_rest_carlos_ferreira_tp3.models.Cliente;
import com.infnet.web_rest_carlos_ferreira_tp3.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente criar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> atualizar(Long id, Cliente cliente) {
        return clienteRepository.findById(id)
                .map(existingCliente -> {
                    existingCliente.setNome(cliente.getNome());
                    existingCliente.setEmail(cliente.getEmail());
                    existingCliente.setEndereco(cliente.getEndereco());
                    return clienteRepository.save(existingCliente);
                });
    }

    public boolean deletar(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
