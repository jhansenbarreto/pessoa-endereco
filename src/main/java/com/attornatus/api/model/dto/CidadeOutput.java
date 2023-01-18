package com.attornatus.api.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Representação dos dados de saída de uma cidade, utilizando o padrão de
 * projetos DTO (Data Transfer Object), evitando expor o modelo do domínio.
 *
 * @author Jhansen Barreto
 */
@Getter
@Setter
public class CidadeOutput {

    public Long id;
    public String nome;
    public EstadoOutput estado;
}
