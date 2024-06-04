package com.infnet.web_rest_carlos_ferreira_tp3.controller;

import com.infnet.web_rest_carlos_ferreira_tp3.models.Funcionario;
import com.infnet.web_rest_carlos_ferreira_tp3.repository.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FuncionarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    @Test
    public void deveCriarFuncionario() throws Exception {
        String funcionarioJson = "{\"nome\": \"Carlos Ferreira\", \"email\": \"carlos@example.com\", \"cargo\": \"Developer\", \"salario\": 5000}";

        mockMvc.perform(post("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(funcionarioJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is("Carlos Ferreira")))
                .andExpect(jsonPath("$.email", is("carlos@example.com")))
                .andExpect(jsonPath("$.cargo", is("Developer")))
                .andExpect(jsonPath("$.salario", is(5000))); // Compare as integers

        // Verificar no repositório se o funcionário foi salvo
        Funcionario funcionario = funcionarioRepository.findByEmail("carlos@example.com").orElseThrow();
        assert funcionario.getNome().equals("Carlos Ferreira");
    }

    @Test
    @Transactional
    public void deveListarFuncionarios() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Carlos Ferreira");
        funcionario.setEmail("carlos@example.com");
        funcionario.setCargo("Developer");
        funcionario.setSalario(new BigDecimal(5000));
        funcionarioRepository.save(funcionario);

        mockMvc.perform(get("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", is("Carlos Ferreira")));
    }

    @Test
    @Transactional
    public void deveBuscarFuncionarioPorId() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Carlos Ferreira");
        funcionario.setEmail("carlos@example.com");
        funcionario.setCargo("Developer");
        funcionario.setSalario(new BigDecimal(5000));
        funcionario = funcionarioRepository.save(funcionario);

        mockMvc.perform(get("/funcionarios/{id}", funcionario.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Carlos Ferreira")));
    }

    @Test
    @Transactional
    public void deveAtualizarFuncionario() throws Exception {
        // Primeiro, crie um funcionário para ser atualizado
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Carlos Ferreira");
        funcionario.setEmail("carlos@example.com");
        funcionario.setCargo("Developer");
        funcionario.setSalario(new BigDecimal(5000));
        funcionario = funcionarioRepository.save(funcionario);

        String funcionarioAtualizadoJson = "{\"nome\": \"Carlos Ferreira Updated\", \"email\": \"carlos@example.com\", \"cargo\": \"Senior Developer\", \"salario\": 7000}";

        mockMvc.perform(put("/funcionarios/{id}", funcionario.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(funcionarioAtualizadoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Carlos Ferreira Updated")))
                .andExpect(jsonPath("$.email", is("carlos@example.com")))
                .andExpect(jsonPath("$.cargo", is("Senior Developer")))
                .andExpect(jsonPath("$.salario", is(7000)));

        // Verificar no repositório se o funcionário foi atualizado
        Funcionario funcionarioAtualizado = funcionarioRepository.findById(funcionario.getId()).orElseThrow();
        assertEquals("Carlos Ferreira Updated", funcionarioAtualizado.getNome());
        assertEquals("Senior Developer", funcionarioAtualizado.getCargo());
        assertEquals(0, funcionarioAtualizado.getSalario().compareTo(new BigDecimal("7000")));
    }

    @Test
    @Transactional
    public void deveDeletarFuncionario() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Carlos Ferreira");
        funcionario.setEmail("carlos@example.com");
        funcionario.setCargo("Developer");
        funcionario.setSalario(new BigDecimal(5000));
        funcionario = funcionarioRepository.save(funcionario);

        mockMvc.perform(delete("/funcionarios/{id}", funcionario.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Optional<Funcionario> deletedFuncionario = funcionarioRepository.findById(funcionario.getId());
        assert deletedFuncionario.isEmpty();
    }
}
