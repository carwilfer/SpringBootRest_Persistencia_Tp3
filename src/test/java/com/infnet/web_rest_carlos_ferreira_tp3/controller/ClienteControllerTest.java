package com.infnet.web_rest_carlos_ferreira_tp3.controller;

import com.infnet.web_rest_carlos_ferreira_tp3.models.Cliente;
import com.infnet.web_rest_carlos_ferreira_tp3.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void deveCriarCliente() throws Exception {
        String clienteJson = "{\"nome\": \"Maria Silva\", \"email\": \"maria@example.com\", \"endereco\": \"Rua A, 123\"}";

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is("Maria Silva")))
                .andExpect(jsonPath("$.email", is("maria@example.com")))
                .andExpect(jsonPath("$.endereco", is("Rua A, 123")));

        Optional<Cliente> cliente = clienteRepository.findById(1L);
        assert cliente.isPresent();
        assert cliente.get().getNome().equals("Maria Silva");
    }

    @Test
    public void deveListarClientes() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Maria Silva");
        cliente.setEmail("maria@example.com");
        cliente.setEndereco("Rua A, 123");
        clienteRepository.save(cliente);

        mockMvc.perform(get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", is("Maria Silva")));
    }

    @Test
    public void deveBuscarClientePorId() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Maria Silva");
        cliente.setEmail("maria@example.com");
        cliente.setEndereco("Rua A, 123");
        cliente = clienteRepository.save(cliente);

        mockMvc.perform(get("/clientes/{id}", cliente.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Maria Silva")));
    }

    @Test
    public void deveAtualizarCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Maria Silva");
        cliente.setEmail("maria@example.com");
        cliente.setEndereco("Rua A, 123");
        cliente = clienteRepository.save(cliente);

        String updatedClienteJson = "{\"nome\": \"Maria Silva Updated\", \"email\": \"maria@example.com\", \"endereco\": \"Rua B, 456\"}";

        mockMvc.perform(put("/clientes/{id}", cliente.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedClienteJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Maria Silva Updated")))
                .andExpect(jsonPath("$.endereco", is("Rua B, 456")));
    }

    @Test
    public void deveDeletarCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Maria Silva");
        cliente.setEmail("maria@example.com");
        cliente.setEndereco("Rua A, 123");
        cliente = clienteRepository.save(cliente);

        mockMvc.perform(delete("/clientes/{id}", cliente.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Optional<Cliente> deletedCliente = clienteRepository.findById(cliente.getId());
        assert deletedCliente.isEmpty();
    }
}
