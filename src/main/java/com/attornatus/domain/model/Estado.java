package com.attornatus.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Representação de um estado. Modelo de domínio do projeto.
 *
 * @author Jhansen Barreto
 */
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Estado {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false, unique = true, length = 2)
    private String uf;

    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades;
}
