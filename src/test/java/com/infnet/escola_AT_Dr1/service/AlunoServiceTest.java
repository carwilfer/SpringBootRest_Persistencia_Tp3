package com.infnet.escola_AT_Dr1.service;

import com.infnet.escola_AT_Dr1.model.Aluno;
import com.infnet.escola_AT_Dr1.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AlunoServiceTest {

    @MockBean
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoService alunoService;

    @Test
    public void testCadastrarAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome("João");
        aluno.setCpf("12345678900");
        aluno.setEmail("joao@example.com");
        aluno.setTelefone("123456789");
        aluno.setEndereco("Rua A");

        Mockito.when(alunoRepository.save(aluno)).thenReturn(aluno);

        Aluno novoAluno = alunoService.cadastrarAluno(aluno);
        assertNotNull(novoAluno);
        assertEquals("João", novoAluno.getNome());
    }

    @Test
    public void testListarAlunos() {
        Aluno aluno1 = new Aluno();
        aluno1.setNome("João");
        aluno1.setCpf("12345678900");
        aluno1.setEmail("joao@example.com");
        aluno1.setTelefone("123456789");
        aluno1.setEndereco("Rua A");

        Aluno aluno2 = new Aluno();
        aluno2.setNome("Maria");
        aluno2.setCpf("09876543211");
        aluno2.setEmail("maria@example.com");
        aluno2.setTelefone("987654321");
        aluno2.setEndereco("Rua B");

        Mockito.when(alunoRepository.findAll()).thenReturn(Arrays.asList(aluno1, aluno2));

        List<Aluno> alunos = alunoService.listarAlunos();
        assertNotNull(alunos);
        assertEquals(2, alunos.size());
    }
}
