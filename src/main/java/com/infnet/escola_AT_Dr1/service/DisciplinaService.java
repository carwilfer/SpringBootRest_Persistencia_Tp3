package com.infnet.escola_AT_Dr1.service;

import com.infnet.escola_AT_Dr1.model.Disciplina;
import com.infnet.escola_AT_Dr1.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Disciplina cadastrarDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public Disciplina buscarDisciplinaPorId(String id) {
        return disciplinaRepository.findById(id).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public Disciplina atualizarDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public void deletarDisciplina(String id) {
        disciplinaRepository.deleteById(id);
    }
}
