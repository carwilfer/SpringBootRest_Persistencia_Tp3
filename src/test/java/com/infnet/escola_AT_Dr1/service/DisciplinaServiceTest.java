package com.infnet.escola_AT_Dr1.service;

import com.infnet.escola_AT_Dr1.model.Disciplina;
import com.infnet.escola_AT_Dr1.repository.DisciplinaRepository;
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
public class DisciplinaServiceTest {

    @MockBean
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private DisciplinaService disciplinaService;

    @Test
    public void testCadastrarDisciplina() {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome("Matemática");
        disciplina.setCodigo("MAT101");

        Mockito.when(disciplinaRepository.save(disciplina)).thenReturn(disciplina);

        Disciplina novaDisciplina = disciplinaService.cadastrarDisciplina(disciplina);
        assertNotNull(novaDisciplina);
        assertEquals("Matemática", novaDisciplina.getNome());
    }

    @Test
    public void testListarDisciplinas() {
        Disciplina disciplina1 = new Disciplina();
        disciplina1.setNome("Matemática");
        disciplina1.setCodigo("MAT101");

        Disciplina disciplina2 = new Disciplina();
        disciplina2.setNome("Física");
        disciplina2.setCodigo("FIS101");

        Mockito.when(disciplinaRepository.findAll()).thenReturn(Arrays.asList(disciplina1, disciplina2));

        List<Disciplina> disciplinas = disciplinaService.listarDisciplinas();
        assertNotNull(disciplinas);
        assertEquals(2, disciplinas.size());
    }
}
