package com.attornatus.api.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Representação dos dados de saída de um estado, utilizando o padrão de
 * projetos DTO (Data Transfer Object), evitando expor o modelo do domínio.
 *
 * @author Jhansen Barreto
 */
@Getter
@Setter
public class EstadoOutput {

    public Long id;
    public String nome;
    public String uf;
}
