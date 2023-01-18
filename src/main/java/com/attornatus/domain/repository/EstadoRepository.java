package com.attornatus.domain.repository;

import com.attornatus.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório do modelo Estado.
 *
 * @author Jhansen Barreto
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
