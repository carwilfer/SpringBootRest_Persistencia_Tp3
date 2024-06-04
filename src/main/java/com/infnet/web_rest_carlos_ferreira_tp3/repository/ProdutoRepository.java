package com.infnet.web_rest_carlos_ferreira_tp3.repository;

import com.infnet.web_rest_carlos_ferreira_tp3.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
