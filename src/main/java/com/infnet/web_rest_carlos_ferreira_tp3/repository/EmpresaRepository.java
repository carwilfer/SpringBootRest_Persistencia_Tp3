package com.infnet.web_rest_carlos_ferreira_tp3.repository;

import com.infnet.web_rest_carlos_ferreira_tp3.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
