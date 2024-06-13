package com.infnet.escola_AT_Dr1.service;

import com.infnet.escola_AT_Dr1.model.Nota;
import com.infnet.escola_AT_Dr1.repository.NotaRepository;
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
public class NotaServiceTest {

    @MockBean
    private NotaRepository notaRepository;

    @Autowired
    private NotaService notaService;

    @Test
    public void testAtribuirNota() {
        Nota nota = new Nota();
        nota.setAlunoId("1");
        nota.setDisciplinaId("1");
        nota.setValor(8.5);

        Mockito.when(notaRepository.save(nota)).thenReturn(nota);

        Nota novaNota = notaService.atribuirNota(nota);
        assertNotNull(novaNota);
        assertEquals(8.5, novaNota.getValor());
    }

    @Test
    public void testListarAprovados() {
        Nota nota1 = new Nota();
        nota1.setAlunoId("1");
        nota1.setDisciplinaId("1");
        nota1.setValor(8.5);

        Nota nota2 = new Nota();
        nota2.setAlunoId("2");
        nota2.setDisciplinaId("1");
        nota2.setValor(9.0);

        Mockito.when(notaRepository.findByDisciplinaId("1")).thenReturn(Arrays.asList(nota1, nota2));

        List<Nota> aprovados = notaService.listarAprovados("1");
        assertNotNull(aprovados);
        assertEquals(2, aprovados.size());
    }

    @Test
    public void testListarReprovados() {
        Nota nota1 = new Nota();
        nota1.setAlunoId("1");
        nota1.setDisciplinaId("1");
        nota1.setValor(5.5);

        Nota nota2 = new Nota();
        nota2.setAlunoId("2");
        nota2.setDisciplinaId("1");
        nota2.setValor(6.0);

        Mockito.when(notaRepository.findByDisciplinaId("1")).thenReturn(Arrays.asList(nota1, nota2));

        List<Nota> reprovados = notaService.listarReprovados("1");
        assertNotNull(reprovados);
        assertEquals(2, reprovados.size());
    }
}
