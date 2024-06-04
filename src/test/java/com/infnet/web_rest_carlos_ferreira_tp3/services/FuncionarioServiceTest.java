package com.infnet.web_rest_carlos_ferreira_tp3.services;

import com.infnet.web_rest_carlos_ferreira_tp3.models.Funcionario;
import com.infnet.web_rest_carlos_ferreira_tp3.repository.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioService funcionarioService;

    public FuncionarioServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarFuncionarioPorId() {
        Long funcionarioId = 1L;
        Funcionario funcionario = new Funcionario();
        funcionario.setId(funcionarioId);
        funcionario.setNome("Teste Funcionario");
        when(funcionarioRepository.findById(funcionarioId)).thenReturn(Optional.of(funcionario));

        Optional<Funcionario> funcionarioEncontrado = funcionarioService.buscarPorId(funcionarioId);

        assertEquals(funcionario, funcionarioEncontrado.orElse(null));
        verify(funcionarioRepository, times(1)).findById(funcionarioId);
    }

    @Test
    public void testListarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        Funcionario funcionario1 = new Funcionario();
        funcionario1.setId(1L);
        funcionario1.setNome("Funcionario 1");
        funcionario1.setEmail("funcionario1@example.com");
        funcionario1.setCargo("Cargo 1");
        funcionario1.setSalario(new BigDecimal(5000));
        funcionarios.add(funcionario1);

        Funcionario funcionario2 = new Funcionario();
        funcionario2.setId(2L);
        funcionario2.setNome("Funcionario 2");
        funcionario2.setEmail("funcionario2@example.com");
        funcionario2.setCargo("Cargo 2");
        funcionario2.setSalario(new BigDecimal(6000));
        funcionarios.add(funcionario2);

        when(funcionarioRepository.findAll()).thenReturn(funcionarios);

        List<Funcionario> funcionariosEncontrados = funcionarioService.listar();

        assertEquals(funcionarios.size(), funcionariosEncontrados.size());
        assertTrue(funcionariosEncontrados.containsAll(funcionarios));
        verify(funcionarioRepository, times(1)).findAll();
    }
}