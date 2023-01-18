package com.attornatus.domain.repository;

import com.attornatus.domain.model.Endereco;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

/**
 * Repositório do modelo Endereco.
 *
 * @author Jhansen Barreto
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("FROM Endereco e WHERE e.pessoa.id = :pessoaId AND e.id = :enderecoId")
    Optional<Endereco> findById(Long enderecoId, Long pessoaId);
}
