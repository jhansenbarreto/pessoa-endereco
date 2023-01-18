package com.attornatus.domain.repository;

import com.attornatus.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório do modelo Pessoa.
 *
 * @author Jhansen Barreto
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
