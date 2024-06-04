package com.infnet.web_rest_carlos_ferreira_tp3.repository;

import com.infnet.web_rest_carlos_ferreira_tp3.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByEmail(String email);
}
