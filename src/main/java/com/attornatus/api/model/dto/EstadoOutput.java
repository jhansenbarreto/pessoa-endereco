package com.attornatus.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Representação de um estado fornecido na solicitação do recurso.")
public class EstadoOutput {

    @Schema(description = "ID (identificador) do estado", example = "1")
    private Long id;
    
    @Schema(description = "Nome do estado", example = "Sergipe")
    private String nome;
    
    @Schema(description = "UF do estado", example = "SE")    
    private String uf;
}
