package com.attornatus.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Representação de uma cidade fornecida na solicitação do recurso.")
public class CidadeOutput {

    @Schema(description = "ID (identificador) da cidade", example = "1")
    private Long id;
    
    @Schema(description = "Nome da cidade", example = "Aracaju")
    private String nome;
    
    @Schema(description = "Representação do estado")
    private EstadoOutput estado;
}
