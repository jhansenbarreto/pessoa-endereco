package com.attornatus.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Representação de uma pessoa. Modelo de domínio do projeto.
 *
 * @author Jhansen Barreto
 */
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Pessoa {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Date dataNascimento;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.REMOVE)
    private List<Endereco> enderecos;
}
