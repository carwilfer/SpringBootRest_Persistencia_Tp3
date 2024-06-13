package com.infnet.escola_AT_Dr1.repository;

import com.infnet.escola_AT_Dr1.model.Aluno;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlunoRepository extends MongoRepository<Aluno, String> {
}
