package com.infnet.escola_AT_Dr1.repository;

import com.infnet.escola_AT_Dr1.model.Disciplina;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DisciplinaRepository extends MongoRepository<Disciplina, String> {
}
