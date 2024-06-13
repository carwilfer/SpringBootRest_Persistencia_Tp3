package com.infnet.escola_AT_Dr1.service;

import com.infnet.escola_AT_Dr1.model.Nota;
import com.infnet.escola_AT_Dr1.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public Nota atribuirNota(Nota nota) {
        return notaRepository.save(nota);
    }

    public List<Nota> listarNotasPorDisciplina(String disciplinaId) {
        return notaRepository.findByDisciplinaId(disciplinaId);
    }

    public List<Nota> listarNotasPorAlunoEDisciplina(String alunoId, String disciplinaId) {
        return notaRepository.findByAlunoIdAndDisciplinaId(alunoId, disciplinaId);
    }

    public List<Nota> listarAprovados(String disciplinaId) {
        return notaRepository.findByDisciplinaId(disciplinaId)
                .stream()
                .filter(nota -> nota.getValor() >= 7)
                .collect(Collectors.toList());
    }

    public List<Nota> listarReprovados(String disciplinaId) {
        return notaRepository.findByDisciplinaId(disciplinaId)
                .stream()
                .filter(nota -> nota.getValor() < 7)
                .collect(Collectors.toList());
    }
}
