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
 * Representação de um endereço. Modelo de domínio do projeto.
 *
 * @author Jhansen Barreto
 */
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Endereco {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean principal = Boolean.FALSE;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false, length = 8)
    private String cep;

    @Column(nullable = false)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    public void tornarPrincipal() {
        this.setPrincipal(Boolean.TRUE);
    }

    public void deixarPrincipal() {
        this.setPrincipal(Boolean.FALSE);
    }
}
