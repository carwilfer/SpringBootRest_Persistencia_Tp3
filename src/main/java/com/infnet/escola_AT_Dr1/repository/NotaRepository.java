package com.infnet.escola_AT_Dr1.repository;

import com.infnet.escola_AT_Dr1.model.Nota;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotaRepository extends MongoRepository<Nota, String> {
    List<Nota> findByDisciplinaId(String disciplinaId);
    List<Nota> findByAlunoIdAndDisciplinaId(String alunoId, String disciplinaId);
}
