package com.attornatus.api.model.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Representação dos dados de saída de uma pessoa, utilizando o padrão de
 * projetos DTO (Data Transfer Object), evitando expor o modelo do domínio.
 *
 * @author Jhansen Barreto
 */
@Getter
@Setter
public class PessoaOutput {
    
    public Long id;
    public String nome;
    public Date dataNascimento;
    public List<EnderecoOutput> enderecos;
}
