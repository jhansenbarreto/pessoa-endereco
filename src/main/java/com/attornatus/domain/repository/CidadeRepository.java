package com.attornatus.domain.repository;

import com.attornatus.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório do modelo Cidade.
 *
 * @author Jhansen Barreto
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
