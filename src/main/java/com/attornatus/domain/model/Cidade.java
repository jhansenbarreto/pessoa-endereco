package com.attornatus.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Representação de uma cidade. Modelo de domínio do projeto.
 *
 * @author Jhansen Barreto
 */
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Cidade {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;

    @Override
    public String toString() {
        return String.format("%s-%s", nome, estado.getUf());
    }
}
