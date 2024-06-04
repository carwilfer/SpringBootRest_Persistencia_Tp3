package com.infnet.web_rest_carlos_ferreira_tp3.repository;

import com.infnet.web_rest_carlos_ferreira_tp3.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
