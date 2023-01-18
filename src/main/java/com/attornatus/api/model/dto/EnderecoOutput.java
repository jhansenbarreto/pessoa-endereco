package com.attornatus.api.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Representação dos dados de saída de um endereço, utilizando o padrão de
 * projetos DTO (Data Transfer Object), evitando expor o modelo do domínio.
 *
 * @author Jhansen Barreto
 */
@Getter
@Setter
public class EnderecoOutput {

    public Long id;
    public Boolean principal;
    public String logradouro;
    public String cep;
    public String numero;
    public String cidade;
}
