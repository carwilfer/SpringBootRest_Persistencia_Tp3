package com.infnet.escola_AT_Dr1.service;

import com.infnet.escola_AT_Dr1.model.Aluno;
import com.infnet.escola_AT_Dr1.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno cadastrarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno buscarAlunoPorId(String id) {
        return alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno atualizarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public void deletarAluno(String id) {
        alunoRepository.deleteById(id);
    }
}
