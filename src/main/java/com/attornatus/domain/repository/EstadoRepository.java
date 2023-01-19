package com.attornatus.domain.repository;

import com.attornatus.domain.model.Estado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repositório do modelo Estado.
 *
 * @author Jhansen Barreto
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    
    @Query("FROM Estado e WHERE e.nome = :nome OR e.uf = :uf")
    List<Estado> findByNomeOrUf(String nome, String uf);
}
